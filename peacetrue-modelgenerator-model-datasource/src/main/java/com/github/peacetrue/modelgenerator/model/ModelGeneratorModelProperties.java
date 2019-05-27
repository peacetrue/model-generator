package com.github.peacetrue.modelgenerator.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.model-generator")
public class ModelGeneratorModelProperties {
    /** 忽略的模型信息，根据模型名称匹配 */
    private List<String> ignoredModels;
}
