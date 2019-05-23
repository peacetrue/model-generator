package com.github.peacetrue.modelgenerator;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Objects;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ModelGeneratorProperties.class)
public class ModelGeneratorConfiguration {

    private ModelGeneratorProperties properties;

    public ModelGeneratorConfiguration(ModelGeneratorProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Bean
    @ConditionalOnMissingBean(ModelGenerator.class)
    public ModelGenerator modelGenerator() {
        return new ModelGeneratorImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ModelTemplateGenerator.class)
    public ModelTemplateGenerator modelTemplateGenerator() {
        return new ModelTemplateGeneratorImpl();
    }

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.DEFAULT_RUNTIME_LOG_NAME, "model.generator");
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath,file");
        velocityEngine.setProperty("resource.loader.classpath.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty("resource.loader.file.class", FileResourceLoader.class.getName());
        velocityEngine.init();
        return velocityEngine;
    }

    @Bean
    @ConditionalOnMissingBean(ExpressionParser.class)
    public ExpressionParser expressionParser() {
        return new SpelExpressionParser();
    }

}
