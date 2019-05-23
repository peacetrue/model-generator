package com.github.peacetrue.modelgenerator;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author xiayx
 */
public class ModelTemplateGeneratorImpl implements ModelTemplateGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private VelocityEngine velocityEngine;
    @Value("${peacetrue.model-generator.id-property:id}")
    private String idProperty;

    @Override
    public void generate(Model model, Template template) {
        logger.info("使用模型[{}]渲染模板[{}]", model, template);

        VelocityContext velocityContext = new VelocityContext();
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(model.getClass());
        Arrays.stream(descriptors).forEach(descriptor -> {
            velocityContext.put(descriptor.getName(), ReflectionUtils.invokeMethod(descriptor.getReadMethod(), model));
        });
        model.getProperties().stream().filter(modelProperty -> modelProperty.getName().equals(idProperty))
                .findAny().ifPresent(modelProperty -> velocityContext.put("idProperty", modelProperty));
        if (template instanceof JavaTemplate) {
            velocityContext.put("packageName", ((JavaTemplate) template).getPackageName());
        }
        velocityContext.put("lowerName", StringUtils.uncapitalize(model.getName()));


        logger.debug("在位置[{}]处生成文件", template.getOutputPath());

        try {
            Path folderPath = Paths.get(template.getOutputPath()).getParent();
            if (!Files.exists(folderPath)) Files.createDirectories(folderPath);
            FileWriter fileWriter = new FileWriter(template.getOutputPath());
            boolean evaluate = velocityEngine.evaluate(velocityContext, fileWriter, "model-generator", template.getContent());
            logger.debug("渲染[{}]", evaluate);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
