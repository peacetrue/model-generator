package com.github.peacetrue.modelgenerator;

import com.github.peacetrue.modelgenerator.template.ModelGeneratorTemplateConfiguration;
import com.github.peacetrue.modelgenerator.template.aum.ModelGeneratorTemplateAumConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ModelGeneratorConfiguration.class,
        ModelGeneratorTemplateConfiguration.class,
        ModelGeneratorTemplateAumConfiguration.class,
        PropertyPlaceholderAutoConfiguration.class
})
@TestPropertySource("classpath:application.properties")
public class ModelGeneratorImplTest {

    @Autowired
    private ModelGenerator modelGenerator;

    @Test
    public void generate() {
        modelGenerator.generate(ModelTemplateGeneratorImplTest.model);
    }

}
