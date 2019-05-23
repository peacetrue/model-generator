package com.github.peacetrue.modelgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public void generate(Model model) {
        logger.info("生成模型[{}]的相关文件", model);

        List<Template> templates = templateSupplier.getTemplates();
        logger.debug("共取得[{}]个模板", templates.size());
        for (Template template : templates) {
            modelTemplateGenerator.generate(model, template);
        }
    }
}
