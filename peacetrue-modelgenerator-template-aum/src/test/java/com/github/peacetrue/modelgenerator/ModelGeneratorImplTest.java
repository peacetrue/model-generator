package com.github.peacetrue.modelgenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ModelGeneratorConfiguration.class,
        ModuleGeneratorTemplateConfiguration.class
})
@TestPropertySource("classpath:application.properties")
public class ModelGeneratorImplTest {

    @Autowired
    private ModelGenerator modelGenerator;

    @Test
    public void generate() {
        modelGenerator.generate(ModelTemplateGeneratorImplTest.model);
        modelGenerator.generate(ModelTemplateGeneratorImplTest.model2);
    }

}
