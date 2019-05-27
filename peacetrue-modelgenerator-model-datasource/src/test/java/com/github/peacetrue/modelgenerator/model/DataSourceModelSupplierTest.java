package com.github.peacetrue.modelgenerator.model;

import com.github.peacetrue.modelgenerator.Model;
import com.github.peacetrue.modelgenerator.ModelSupplier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ModelGeneratorModelAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@PropertySource("classpath:/application.properties")
public class DataSourceModelSupplierTest {

    @Autowired
    private ModelSupplier dataSourceModelSupplier;

    @Test
    public void getModels() {
        List<Model> models = dataSourceModelSupplier.getModels();
        System.out.println(models);
        Assert.assertEquals(1, models.size());
        Assert.assertEquals(7, models.get(0).getProperties().size());
    }

}