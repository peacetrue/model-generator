package com.github.peacetrue.modelgenerator.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author xiayx
 */
@Configuration
@EnableConfigurationProperties(ModelGeneratorModelProperties.class)
public class ModelGeneratorModelAutoConfiguration {

    public static final String DATA_SOURCE_MODEL_SUPPLIER = "dataSourceModelSupplier";

    private ModelGeneratorModelProperties properties;

    public ModelGeneratorModelAutoConfiguration(ModelGeneratorModelProperties properties) {
        this.properties = Objects.requireNonNull(properties);
    }

    @Bean
    @ConditionalOnMissingBean(name = DATA_SOURCE_MODEL_SUPPLIER)
    public DataSourceModelSupplier dataSourceModelSupplier() {
        return new DataSourceModelSupplier(properties.getIgnoredModels());
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
