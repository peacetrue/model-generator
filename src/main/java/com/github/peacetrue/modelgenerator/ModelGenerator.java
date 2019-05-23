package com.github.peacetrue.modelgenerator;

/**
 * 模型生成器
 *
 * @author xiayx
 * @see ModelTemplateGenerator
 */
public interface ModelGenerator {

    /**
     * 根据模型信息生成相关的文件
     *
     * @param model 模型信息
     */
    void generate(Model model);

}
