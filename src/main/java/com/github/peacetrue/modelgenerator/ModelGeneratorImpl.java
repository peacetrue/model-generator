package com.github.peacetrue.modelgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;

import java.util.List;

/**
 * @author xiayx
 */
public class ModelGeneratorImpl implements ModelGenerator {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ModelTemplateGenerator modelTemplateGenerator;
    @Autowired
    private TemplateSupplier templateSupplier;
    @Autowired
    private ExpressionParser expressionParser;

    @Override
    public void generate(Model model) {
        logger.info("根据模型[{}]生成相关文件", model);

        List<Template> templates = templateSupplier.getTemplates();
        logger.debug("共取得[{}]个模板", templates.size());
        for (Template template : templates) {
            logger.debug("处理模板[{}]SPEL表达式", template);
            Expression expression = expressionParser.parseExpression(template.getOutputPath(), ParserContext.TEMPLATE_EXPRESSION);
            template.setOutputPath(expression.getValue(model, String.class));
            if (template instanceof JavaTemplate) {
                JavaTemplate javaTemplate = (JavaTemplate) template;
                expression = expressionParser.parseExpression(javaTemplate.getPackageName(), ParserContext.TEMPLATE_EXPRESSION);
                javaTemplate.setPackageName(expression.getValue(model, String.class));
            }
            modelTemplateGenerator.generate(model, template);
        }
    }
}
