package com.github.peacetrue;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.dynamic.sql.update.UpdateDSL;

import javax.annotation.Generated;

import static com.github.peacetrue.TestDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author xiayx
 */
@Mapper
public interface TestMapperX extends TestMapper {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeyWithoutCreate(Test record) {
        return UpdateDSL.updateWithMapper(this::update, test)
                .set(code).equalTo(record::getCode)
                .set(name).equalTo(record::getName)
                .set(modifiedTime).equalTo(record::getModifiedTime)
                .set(modifierId).equalTo(record::getModifierId)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

}
