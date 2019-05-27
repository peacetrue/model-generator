package com.github.peacetrue.modelgenerator.model;

import com.github.peacetrue.modelgenerator.Model;
import com.github.peacetrue.modelgenerator.ModelProperty;
import com.github.peacetrue.modelgenerator.ModelSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 从数据源中获取模型信息
 *
 * @author xiayx
 */
public class DataSourceModelSupplier implements ModelSupplier {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TableNameToModelName tableNameToModelName;
    @Autowired
    private ColumnNameToPropertyName columnNameToPropertyName;
    @Autowired
    private SqlTypeToJavaType sqlTypeToJavaType;
    private List<String> ignoredModels = new ArrayList<>(0);

    public DataSourceModelSupplier() {
    }

    public DataSourceModelSupplier(@Nullable List<String> ignoredModels) {
        if (!CollectionUtils.isEmpty(ignoredModels)) this.ignoredModels.addAll(ignoredModels);
    }

    @Override
    public List<Model> getModels() {
        logger.info("从数据源[{}]中获取模型信息", dataSource);
        try {
            return getModelsThrow();
        } catch (SQLException e) {
            throw new IllegalStateException(String.format("从数据源[%s]中读取模型异常", dataSource), e);
        }
    }

    private List<Model> getModelsThrow() throws SQLException {
        List<Model> models = new ArrayList<>();
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString(3);
            logger.info("读取表[{}]", tableName);
            String modelName = tableNameToModelName.getModelName(tableName);
            if (ignoredModels.contains(modelName)) {
                logger.debug("模型[{}]已被配置为忽略", modelName);
                continue;
            }

            Model model = new Model();
            model.setName(modelName);
            model.setComment(tables.getString(5));
            model.setProperties(new ArrayList<>());
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString(4);
                logger.info("读取列[{}]", columnName);
                model.getProperties().add(new ModelProperty(
                        columnNameToPropertyName.getPropertyName(columnName),
                        sqlTypeToJavaType.getJavaType(columns.getInt(5)),
                        columns.getString(12)
                ));
            }
            logger.debug("构造模型[{}]", model);
            models.add(model);
        }
        return models;
    }

    public List<String> getIgnoredModels() {
        return ignoredModels;
    }

    public void setIgnoredModels(List<String> ignoredModels) {
        this.ignoredModels = ignoredModels;
    }
}
