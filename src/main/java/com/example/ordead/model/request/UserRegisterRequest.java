package com.example.ordead.model.request;

import lombok.Data;



/**
 * 用户注册请求参数类（接收前端传递的注册数据）
 */
@Data
public class UserRegisterRequest {
    /**
     * 用户名（非空、长度4-16位、仅支持字母数字下划线）
     */
    private String userName;

    /**
     * 密码（非空、长度6-20位）
     */
    private String userPassword;

    /**
     * 确认密码（非空，需与密码一致，后续业务逻辑校验）
     */
    private String checkPassword;

    /**
     * 手机号（可选，增加正则校验保证格式合法）
     */
    private String phone;
}