package com.github.peacetrue.modelgenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xiayx
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class JavaTemplateImpl extends TemplateImpl implements JavaTemplate {

    /** 包名 */
    private String packageName;

    public JavaTemplateImpl(String packageName) {
        this.packageName = packageName;
    }

    public JavaTemplateImpl(String content, String filePath, String packageName) {
        super(content, filePath);
        this.packageName = packageName;
    }

    public static JavaTemplateImpl build(String content, String folderPath, String packageName, String sourceName) {
        return new JavaTemplateImpl(content, String.format("%s/%s/%s.java", folderPath, packageName.replaceAll("\\.", "/"), sourceName), packageName);
    }
}
