package com.github.peacetrue.modelgenerator.template;

import com.github.peacetrue.modelgenerator.TemplateSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ModelGeneratorTemplateAutoConfiguration.class)
@TestPropertySource("classpath:application.properties")
public class TemplateSupplierTest {

    @Autowired
    private TemplateSupplier templateSupplier;

    @Test
    public void getTemplates() {
        Assert.assertEquals(1, templateSupplier.getTemplates().size());
    }
}