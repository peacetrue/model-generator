package com.github.peacetrue;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* @author xiayx
*/
@Data
public class TestVO implements Serializable {

    /**主键*/
    private String id;
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
