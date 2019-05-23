package com.github.peacetrue;

import com.github.peacetrue.result.exception.ResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nullable;

/**
 * 测试服务接口
 *
 * @author xiayx
 */
public interface TestService {

    /** 新增信息 */
    TestVO add(TestAddDTO dto) throws ResultException;

    /** 分页查询信息 */
    Page<TestVO> query(@Nullable TestQueryDTO dto, Pageable pageable) throws ResultException;

    /** 根据主键获取信息 */
    TestVO get(TestGetDTO dto) throws ResultException;

    /** 修改信息 */
    void modify(TestModifyDTO dto) throws ResultException;

    /** 删除信息 */
    void delete(TestDeleteDTO dto) throws ResultException;

}