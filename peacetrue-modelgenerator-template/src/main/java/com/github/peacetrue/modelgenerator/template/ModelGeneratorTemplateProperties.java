package com.github.peacetrue.modelgenerator.template;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author xiayx
 */
@Data
@ConfigurationProperties(prefix = "peacetrue.model-generator")
public class ModelGeneratorTemplateProperties {

    /*主要信息*/
    /** 项目路径，将作为{@link Template#outputPath}的前缀，不要以'/'结尾 */
    private String projectPath;
    /** 默认的模板信息。{@link #templates}中为空的属性将会使用{@link #template}中对应的属性填充 */
    private Template template;
    /** 模板信息 */
    private List<Template> templates;

    /*辅助信息*/
    /** 组名 */
    private String groupName;
    /** 公司名 */
    private String companyName = "aum";
    /** 公司名与项目名之间的自定义部分 */
    private String customName1;
    /** 项目名与包名之间的自定义部分 */
    private String customName2 = "modules";
    /** 项目名 */
    private String projectName;
    /** 项目模块名称前缀 */
    private String modulePrefix;
    /** 包前缀 */
    private String packagePrefix;
    /** 包前缀路径 */
    private String packagePrefixPath;

    @PostConstruct
    public void init() {
        templates.forEach(template -> {
            if (template.getPath() == null) template.setPath(this.template.getPath());
            if (template.getPackageName() == null) template.setPackageName(this.template.getPackageName());
            if (template.getOutputPath() == null) template.setOutputPath(this.template.getOutputPath());

            if (template.getPackageName() != null) {
                template.setPackageName(template.getPackageName().replaceAll("\\.\\.", "."));
            }
        });
    }

    /** 模板信息 */
    @Data
    public static class Template {
        /** 模板路径.{@link org.springframework.core.io.ResourceLoader#getResource(String)} */
        private String path;
        /** 包名 */
        private String packageName;
        /** 生成文件存储位置 */
        private String outputPath;
    }


}
