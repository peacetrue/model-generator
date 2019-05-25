package com.github.peacetrue.modelgenerator.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiayx
 */
@Configuration
public class ModelGeneratorModelAutoConfiguration {

    public static final String DATA_SOURCE_MODEL_SUPPLIER = "dataSourceModelSupplier";

    @Bean
    @ConditionalOnMissingBean(name = DATA_SOURCE_MODEL_SUPPLIER)
    public DataSourceModelSupplier dataSourceModelSupplier() {
        return new DataSourceModelSupplier();
    }

    @Bean
    @ConditionalOnMissingBean(TableNameToModelName.class)
    public TableNameToModelName tableNameToModelName() {
        return new DefaultTableNameToModelName();
    }

    @Bean
    @ConditionalOnMissingBean(ColumnNameToPropertyName.class)
    public ColumnNameToPropertyName columnNameToPropertyName() {
        return new DefaultColumnNameToPropertyName();
    }

    @Bean
    @ConditionalOnMissingBean(SqlTypeToJavaType.class)
    public SqlTypeToJavaType sqlTypeToJavaType() {
        return new DefaultSqlTypeToJavaType();
    }
}
