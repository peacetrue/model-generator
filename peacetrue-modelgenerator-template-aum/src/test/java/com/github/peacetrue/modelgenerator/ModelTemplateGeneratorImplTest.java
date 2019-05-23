package com.github.peacetrue.modelgenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        ModelGeneratorConfiguration.class,
        ModuleGeneratorTemplateConfiguration.class
})
@TestPropertySource("classpath:application.properties")
public class ModelTemplateGeneratorImplTest {

    @Autowired
    private ModelTemplateGenerator modelTemplateGenerator;

    public static Model model = new Model();

    static {
        model.setName("Test");
        model.setComment("测试");
        model.setProperties(Arrays.asList(
                new ModelProperty("id", String.class, "主键"),
                new ModelProperty("code", String.class, "编码"),
                new ModelProperty("name", String.class, "名称"),
                new ModelProperty("createdTime", Date.class, "创建时间"),
                new ModelProperty("creatorId", String.class, "创建者主键"),
                new ModelProperty("modifiedTime", Date.class, "最近修改时间"),
                new ModelProperty("modifierId", String.class, "最近修改者主键")
        ));
    }


    @Test
    public void entity() throws Exception {
        //require lombok
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/Entity.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName()));
    }

    @Test
    public void vo() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/VO.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "VO"));
    }

    @Test
    public void add() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/AddDTO.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "AddDTO"));
    }

    @Test
    public void query() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/QueryDTO.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "QueryDTO"));
    }

    @Test
    public void get() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/GetDTO.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "GetDTO"));
    }

    @Test
    public void modify() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/ModifyDTO.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "ModifyDTO"));
    }

    @Test
    public void delete() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/DeleteDTO.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "DeleteDTO"));
    }

    @Test
    public void service() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/Service.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "Service"));
    }

    @Test
    public void serviceImpl() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/ServiceImpl.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "ServiceImpl"));
    }

    @Test
    public void adminController() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/AdminController.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue", model.getName() + "Controller"));
    }

    @Test
    public void httpController() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/AdminController.vm")));
        modelTemplateGenerator.generate(model, JavaTemplate.build(content,
                "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/java",
                "com.github.peacetrue.http", model.getName() + "Controller"));
    }

    @Test
    public void listPage() throws Exception {
        //require lombok
        //require peacetrue-core
        String content = FileCopyUtils.copyToString(new InputStreamReader(getClass().getResourceAsStream("/page_list.vm")));
        modelTemplateGenerator.generate(model, new Template(content, "/Users/xiayx/Documents/Projects/peacetrue-modelgenerator/src/test/resources/" + model.getName() + ".html"));
    }


}