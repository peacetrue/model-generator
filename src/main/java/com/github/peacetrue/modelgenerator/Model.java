package com.github.peacetrue.modelgenerator;

import lombok.Data;

import java.util.List;

/**
 * 模型
 *
 * @author xiayx
 */
@Data
public class Model {

    /** 名称 */
    private String name;
    /** 属性 */
    private List<ModelProperty> properties;
    /** 注释 */
    private String comment;

}
