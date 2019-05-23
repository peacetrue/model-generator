package com.github.peacetrue.modelgenerator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.model-generator")
public class ModelGeneratorProperties {
    /** 主键属性名 */
    private String idProperty = "id";
}
