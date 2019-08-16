package com.strady.websocketchat.core;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @Author: strady
 * @Date: 2019-08-15
 * @Time: 19:11:50
 * @Description: Domain基类
 */
@Data
@MappedSuperclass
public class BaseDomain {

    /**
     * 主键，Id，为UUID
     */
    @Id
    private String id;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 备注
     */
    private String remark;
}
