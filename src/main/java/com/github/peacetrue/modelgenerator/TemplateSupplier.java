package com.github.peacetrue.modelgenerator;

import java.util.List;

/**
 * 模板供应者
 *
 * @author xiayx
 */
public interface TemplateSupplier {

    /** 获取模板 */
    List<Template> getTemplates();
}
