package com.github.peacetrue.modelgenerator;

import java.util.List;

/**
 * 模型供应者
 *
 * @author xiayx
 */
public interface ModelSupplier {

    /** 获取模型信息 */
    List<Model> getModels();
}
