package com.github.peacetrue.modelgenerator;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 模型
 *
 * @author xiayx
 */
@Data
public class Model {

    /** 名称，UpperCamel格式，例如：UpperCamel */
    private String name;
    /** 属性 */
    private List<ModelProperty> properties;
    /** 注释 */
    private String comment;

    /** 获取lowerCamel格式的名称，例如：lowerCamel */
    public String getLowerCamelName() {
        return StringUtils.uncapitalize(name);
    }

    /** 获取小写名称，例如：lowercamel */
    public String getLowerName() {
        return name.toLowerCase();
    }

}
