package com.github.peacetrue.modelgenerator;

/**
 * 模板模型生成器
 *
 * @author xiayx
 * @see ModelGenerator
 */
public interface ModelTemplateGenerator {

    /**
     * 根据模型信息生成模板指定的文件
     *
     * @param model    模型信息
     * @param template 模板信息
     */
    void generate(Model model, Template template);

}
