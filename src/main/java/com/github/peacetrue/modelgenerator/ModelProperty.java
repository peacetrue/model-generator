package com.github.peacetrue.modelgenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模型属性
 *
 * @author xiayx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelProperty {

    /** 名称 */
    private String name;
    /** 类型 */
    private Class<?> type;
    /** 注释 */
    private String comment;

    /** 获取类型全路径名称 */
    public String getTypeName() {
        return type.getName();
    }

    /** 获取类型名称 */
    public String getTypeSimpleName() {
        return type.getSimpleName();
    }
}
