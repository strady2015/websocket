package com.strady.websocketchat.domain;

import com.strady.websocketchat.core.BaseDomain;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: strady
 * @Date: 2019/7/6
 * @Time: 12:38
 * @Description: User表
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User extends BaseDomain {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String photoUrl;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 盐
     */
    private String salt;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Boolean isDeleted;

}
