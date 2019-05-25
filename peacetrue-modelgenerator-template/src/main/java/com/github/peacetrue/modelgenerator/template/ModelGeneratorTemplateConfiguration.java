package com.github.peacetrue.modelgenerator.template;

import com.github.peacetrue.modelgenerator.JavaTemplateImpl;
import com.github.peacetrue.modelgenerator.Template;
import com.github.peacetrue.modelgenerator.TemplateImpl;
import com.github.peacetrue.modelgenerator.TemplateSupplier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 模板配置
 *
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ModelGeneratorTemplateProperties.class)
@PropertySource("classpath:/peacetrue.modelgenerator.template.properties")
public class ModelGeneratorTemplateConfiguration {

    private ModelGeneratorTemplateProperties properties;

    public ModelGeneratorTemplateConfiguration(ModelGeneratorTemplateProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Bean
    public TemplateSupplier templateSupplier(ResourceLoader resourceLoader) {
        List<Template> templates = properties.getTemplates()
                .stream().map(template -> getTemplate(resourceLoader, template))
                .collect(Collectors.toList());
        return () -> templates;
    }

    private Template getTemplate(ResourceLoader resourceLoader, ModelGeneratorTemplateProperties.Template template) {
        TemplateImpl templateImpl = Optional.ofNullable(template.getPackageName())
                .map(value -> (TemplateImpl) new JavaTemplateImpl(value))
                .orElse(new TemplateImpl());
        templateImpl.setContent(getContent(resourceLoader.getResource(template.getPath())));
        templateImpl.setOutputPath(properties.getProjectPath() + template.getOutputPath());
        return templateImpl;
    }


    private String getContent(Resource resource) {
        try {
            return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
