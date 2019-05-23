package com.github.peacetrue;

import com.github.pagehelper.PageHelper;
import com.github.peacetrue.mybatis.MybatisUtils;
import com.github.peacetrue.mybatis.PageHelperUtils;
import com.github.peacetrue.result.exception.ResultException;
import com.github.peacetrue.spring.util.BeanUtils;
import com.github.peacetrue.util.UUIDUtils;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.github.peacetrue.TestDynamicSqlSupport.test;

/**
* 测试服务实现
* @author xiayx
*/
@Service
public class TestServiceImpl implements TestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestMapperX testMapperX;

    @Override
    public TestVO add(TestAddDTO dto) throws ResultException {
        logger.info("新增信息");
        logger.debug("dto: {}", dto);
        Test record = BeanUtils.toSubclass(dto, Test.class);
        record.setId(UUIDUtils.randomUUID());
        record.setCreatorId(dto.getOperatorId());
        record.setCreatedTime(new Date());
        record.setModifierId(dto.getOperatorId());
        record.setModifiedTime(record.getCreatedTime());
        testMapperX.insertSelective(record);
        return BeanUtils.toSubclass(record, TestVO.class);
    }
    
    @Override
    public Page<TestVO> query(@Nullable TestQueryDTO dto, Pageable pageable) throws ResultException {
        logger.info("分页查询信息");
        logger.debug("dto: {}, page: {}", dto, pageable);
    
        if (dto == null) dto = new TestQueryDTO();
        SelectStatementProvider provider = SqlBuilder.select(MybatisUtils.getSqlColumns(test))
        .from(test)
        .where(test.code, SqlBuilder.isLikeWhenPresent(MybatisUtils.likeValue(dto.getCode())))
        .and(test.name, SqlBuilder.isEqualToWhenPresent(MybatisUtils.likeValue(dto.getName())))
        .and(test.createdTime, SqlBuilder.isGreaterThanOrEqualToWhenPresent(dto.getCreatedTime().getLowerBound()))
        .and(test.createdTime, SqlBuilder.isLessThanOrEqualToWhenPresent(MybatisUtils.endDateValue(dto.getCreatedTime().getUpperBound())))
        .orderBy(MybatisUtils.orders(test, pageable.getSort())).build().render(RenderingStrategy.MYBATIS3);
    
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<Test> entities = testMapperX.selectMany(provider);
        logger.debug("共取得'{}'条记录", entities.size());
        if (entities.isEmpty()) return new PageImpl<>(Collections.emptyList());

        List<TestVO> vos = BeanUtils.replaceAsList(entities, TestVO.class);
        return new PageImpl<>(vos, null, PageHelperUtils.getTotal(entities));
    }
    
    @Override
    public TestVO get(TestGetDTO dto) throws ResultException {
        logger.info("根据'{}'获取信息", dto);
        Test Test = testMapperX.selectByPrimaryKey(dto.getId());
        return Test == null ? null : BeanUtils.toSubclass(Test, TestVO.class);
    }
    
    @Override
    public void modify(TestModifyDTO dto) throws ResultException {
        logger.info("修改信息");
        logger.debug("dto: {}", dto);

        Test record = BeanUtils.toSubclass(dto, Test.class);
        record.setModifierId(dto.getOperatorId());
        record.setModifiedTime(new Date());
        int count = testMapperX.updateByPrimaryKeySelective(record);
        logger.debug("影响'{}'行记录", count);
    }
    
    @Override
    public void delete(TestDeleteDTO dto) throws ResultException {
        logger.info("删除信息");
        logger.debug("dto: {}", dto);
        int count = testMapperX.deleteByPrimaryKey(dto.getId());
        logger.debug("影响'{}'行记录", count);
    }
}
