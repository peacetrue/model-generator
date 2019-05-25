package com.github.peacetrue.modelgenerator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author xiayx
 */
public class ModelTemplateGeneratorImpl implements ModelTemplateGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String idProperty;
    @Autowired
    private VelocityEngine velocityEngine;
    @Autowired
    private ExpressionParser expressionParser;
    @Autowired(required = false)
    private List<TemplatePropertiesSupplier> templatePropertiesSuppliers;

    public ModelTemplateGeneratorImpl() {
        this("id");
    }

    public ModelTemplateGeneratorImpl(String idProperty) {
        this.idProperty = Objects.requireNonNull(idProperty);
    }

    @Override
    public void generate(Model model, Template template) {
        logger.info("使用模型[{}]渲染模板[{}]", model, template);

        VelocityContext velocityContext = new VelocityContext();
        this.setCustomProperties(velocityContext, model);
        this.setModelProperties(velocityContext, model);

        logger.debug("解析输出路径[{}]的表达式", template.getOutputPath());
        Expression expression = expressionParser.parseExpression(template.getOutputPath(), ParserContext.TEMPLATE_EXPRESSION);
        String outputPath = expression.getValue(model, String.class);
        logger.debug("在位置[{}]处生成文件", outputPath);

        if (template instanceof JavaTemplate) {
            JavaTemplate javaTemplate = (JavaTemplate) template;
            expression = expressionParser.parseExpression(javaTemplate.getPackageName(), ParserContext.TEMPLATE_EXPRESSION);
            String packageName = expression.getValue(model, String.class);
            velocityContext.put("packageName", packageName);
        }

        try {
            this.generate(velocityContext, template.getContent(), outputPath);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /** 设置自定义模板属性 */
    private void setCustomProperties(VelocityContext velocityContext, Model model) {
        if (CollectionUtils.isEmpty(templatePropertiesSuppliers)) return;
        templatePropertiesSuppliers.forEach(supplier -> supplier.getTemplateProperties(model).forEach(velocityContext::put));
    }

    /** 设置模型属性 */
    private void setModelProperties(VelocityContext velocityContext, Model model) {
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(model.getClass());
        Arrays.stream(descriptors).forEach(descriptor ->
                velocityContext.put(descriptor.getName(),
                        ReflectionUtils.invokeMethod(descriptor.getReadMethod(), model)));

        model.getProperties().stream().filter(modelProperty -> modelProperty.getName().equals(idProperty))
                .findAny().ifPresent(modelProperty -> velocityContext.put("idProperty", modelProperty));
    }

    /** 生成文件 */
    private void generate(VelocityContext velocityContext, String content, String outputPath) throws IOException {
        Path folderPath = Paths.get(Objects.requireNonNull(outputPath)).getParent();
        if (!Files.exists(folderPath)) Files.createDirectories(folderPath);
        FileWriter fileWriter = new FileWriter(outputPath);
        boolean evaluate = velocityEngine.evaluate(velocityContext, fileWriter, "model-generator", content);
        logger.debug("渲染{}", evaluate ? "完成" : "失败");
        fileWriter.flush();
        fileWriter.close();
    }
}
