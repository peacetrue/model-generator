package com.github.peacetrue.modelgenerator;

import java.util.Map;

/**
 * 模板属性供应者
 *
 * @author xiayx
 */
public interface TemplatePropertiesSupplier {

    /** 获取模板属性 */
    Map<String, Object> getTemplateProperties(Model model);
}
