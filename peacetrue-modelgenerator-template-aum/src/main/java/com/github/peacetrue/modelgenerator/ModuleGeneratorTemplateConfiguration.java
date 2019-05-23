package com.github.peacetrue.modelgenerator;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ModelGeneratorTemplateProperties.class)
public class ModuleGeneratorTemplateConfiguration {

    private ModelGeneratorTemplateProperties properties;

    public ModuleGeneratorTemplateConfiguration(ModelGeneratorTemplateProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Bean
    public TemplateSupplier templateSupplier() {
        List<String[]> javaTemplates = Arrays.asList(
//                new String[]{"Entity.vm",""},
                new String[]{"VO.vm", "service-api"},
                new String[]{"AddDTO.vm", "service-api"},
                new String[]{"QueryDTO.vm", "service-api"},
                new String[]{"GetDTO.vm", "service-api"},
                new String[]{"ModifyDTO.vm", "service-api"},
                new String[]{"DeleteDTO.vm", "service-api"},
                new String[]{"Service.vm", "service-api"},
                new String[]{"ServiceImpl.vm", "service-impl"},
                new String[]{"Controller_Http.vm", "app-http"},
                new String[]{"Controller_Admin.vm", "app-admin"},
                new String[]{"list.vm", "app-admin", "html"},
                new String[]{"detail.vm", "app-admin", "html"}
        );
        List<Template> templates = javaTemplates.stream().map(this::bulid).collect(Collectors.toList());
        return () -> templates;
    }

    public Template bulid(String[] values) {
        if (values.length == 2) {
            //java file
            String filePath = String.format("%s/%s/src/main/java", properties.getProjectSourcePath(), properties.getProjectSourceName() + "-" + values[1]);
            JavaTemplate javaTemplate = new JavaTemplate();
            javaTemplate.setContent(getContent(values[0]));
            javaTemplate.setPackageName(String.format("com.aum.%s.modules.%s", properties.getProjectName(), "#{name.toLowerCase()}"));
            String suffix = values[0].split("\\.")[0].split("_")[0];
            javaTemplate.setOutputPath(filePath + String.format("/com/aum/%s/modules/%s/#{name}%s.java", properties.getProjectName(), "#{name.toLowerCase()}", suffix));
            return javaTemplate;
        } else {
            //html file
            String filePath = String.format("%s/%s/src/main/resources/templates/#{name.toLowerCase()}/%s.html",
                    properties.getProjectSourcePath(),
                    properties.getProjectSourceName() + "-" + values[1],
                    values[0].split("\\.")[0]
            );
            return new Template(getContent(values[0]), filePath);
        }
    }

    private String getContent(String name) {
        try {
            return FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/" + name)));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
