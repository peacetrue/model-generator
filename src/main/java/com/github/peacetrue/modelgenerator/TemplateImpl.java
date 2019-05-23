package com.github.peacetrue.modelgenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiayx
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateImpl implements Template {
    /** 模板内容 */
    private String content;
    /** 模板生成后的存储文件路径 */
    private String outputPath;
}
