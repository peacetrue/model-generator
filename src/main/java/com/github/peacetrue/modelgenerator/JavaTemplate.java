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
public class JavaTemplate extends Template {

    /** 包名 */
    private String packageName;

    public JavaTemplate(String content, String filePath, String packageName) {
        super(content, filePath);
        this.packageName = packageName;
    }

    public static JavaTemplate build(String content, String folderPath, String packageName, String sourceName) {
        return new JavaTemplate(content, String.format("%s/%s/%s.java", folderPath, packageName.replaceAll("\\.", "/"), sourceName), packageName);
    }
}
