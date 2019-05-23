package com.github.peacetrue;

import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试删除DTO
 * @author xiayx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestDeleteDTO extends OperatorCapableImpl<String> {

    /**主键*/
    private String id;
}
