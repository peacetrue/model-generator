package com.github.peacetrue;

import com.github.peacetrue.core.Range;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试查询DTO
 *
 * @author xiayx
 */
@Data
public class TestQueryDTO implements Serializable {


    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 创建时间起止范围 */
    private Range.Date createdTime = new Range.Date();

    /** 创建者主键 */
    private String creatorId;

    /** 最近修改时间起止范围 */
    private Range.Date modifiedTime = new Range.Date();

    /** 最近修改者主键 */
    private String modifierId;

}
