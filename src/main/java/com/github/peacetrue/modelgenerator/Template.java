package com.github.peacetrue.modelgenerator;

/**
 * 模板信息
 *
 * @author xiayx
 */
public interface Template {

    /** 获取模板内容 */
    String getContent();

    /** 获取模板生成文件存储地址 */
    String getOutputPath();
}
