package com.github.peacetrue;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 测试新增DTO
 * @author xiayx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestAddDTO extends OperatorCapableImpl<String> {

    /**编码*/
    private String code;
    /**名称*/
    private String name;
    /**创建时间*/
    private Date createdTime;
    /**创建者主键*/
    private String creatorId;
    /**最近修改时间*/
    private Date modifiedTime;
    /**最近修改者主键*/
    private String modifierId;
}
