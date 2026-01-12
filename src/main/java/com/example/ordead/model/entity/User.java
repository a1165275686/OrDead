package com.example.ordead.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户核心实体类（对应数据库user表，封装用户完整信息）
 */
@Data
public class User {
    /**
     * 用户唯一标识（主键，自增）
     */
    private Long id;

    /**
     * 用户名（唯一）
     */
    private String userName;

    /**
     * 加密后的密码（不可存储明文密码，后续业务逻辑需加密处理）
     */
    private String userPassword;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态（0：正常，1：禁用，默认0）
     */
    private Integer status;

    /**
     * 创建时间（用户注册时间）
     */
    private Date createTime;

    /**
     * 更新时间（用户信息修改时间）
     */
    private Date updateTime;
}