package com.github.peacetrue.modelgenerator;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Paths;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.model-generator")
public class ModelGeneratorTemplateProperties implements InitializingBean {

    private String projectSourcePath;
    private String projectSourceName;
    private String projectName;


    @Override
    public void afterPropertiesSet() throws Exception {
        if (projectSourceName == null) {
            projectSourceName = Paths.get(projectSourcePath).getFileName().toString();
        }
    }

}
