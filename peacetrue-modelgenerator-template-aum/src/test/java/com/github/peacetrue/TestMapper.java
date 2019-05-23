package com.github.peacetrue;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import javax.annotation.Generated;
import java.util.List;

import static com.github.peacetrue.TestDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Mapper
public interface TestMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Test> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("TestResult")
    Test selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="TestResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.VARCHAR),
        @Result(column="modified_time", property="modifiedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.VARCHAR)
    })
    List<Test> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(test);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, test);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, test)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Test record) {
        return insert(SqlBuilder.insert(record)
                .into(test)
                .map(id).toProperty("id")
                .map(code).toProperty("code")
                .map(name).toProperty("name")
                .map(createdTime).toProperty("createdTime")
                .map(creatorId).toProperty("creatorId")
                .map(modifiedTime).toProperty("modifiedTime")
                .map(modifierId).toProperty("modifierId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Test record) {
        return insert(SqlBuilder.insert(record)
                .into(test)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(code).toPropertyWhenPresent("code", record::getCode)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
                .map(creatorId).toPropertyWhenPresent("creatorId", record::getCreatorId)
                .map(modifiedTime).toPropertyWhenPresent("modifiedTime", record::getModifiedTime)
                .map(modifierId).toPropertyWhenPresent("modifierId", record::getModifierId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Test>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, code, name, createdTime, creatorId, modifiedTime, modifierId)
                .from(test);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Test>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, code, name, createdTime, creatorId, modifiedTime, modifierId)
                .from(test);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Test selectByPrimaryKey(String id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, code, name, createdTime, creatorId, modifiedTime, modifierId)
                .from(test)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Test record) {
        return UpdateDSL.updateWithMapper(this::update, test)
                .set(id).equalTo(record::getId)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(creatorId).equalTo(record::getCreatorId)
                .set(modifiedTime).equalTo(record::getModifiedTime)
                .set(modifierId).equalTo(record::getModifierId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Test record) {
        return UpdateDSL.updateWithMapper(this::update, test)
                .set(id).equalToWhenPresent(record::getId)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(creatorId).equalToWhenPresent(record::getCreatorId)
                .set(modifiedTime).equalToWhenPresent(record::getModifiedTime)
                .set(modifierId).equalToWhenPresent(record::getModifierId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Test record) {
        return UpdateDSL.updateWithMapper(this::update, test)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(creatorId).equalTo(record::getCreatorId)
                .set(modifiedTime).equalTo(record::getModifiedTime)
                .set(modifierId).equalTo(record::getModifierId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Test record) {
        return UpdateDSL.updateWithMapper(this::update, test)
                .set(code).equalToWhenPresent(record::getCode)
                .set(name).equalToWhenPresent(record::getName)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(creatorId).equalToWhenPresent(record::getCreatorId)
                .set(modifiedTime).equalToWhenPresent(record::getModifiedTime)
                .set(modifierId).equalToWhenPresent(record::getModifierId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}