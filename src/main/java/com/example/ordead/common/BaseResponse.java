package com.example.ordead.common;

import lombok.Data;

/**
 * 全局统一响应结果类
 *
 * @param <T> 响应数据体的泛型，支持不同类型数据返回
 */
@Data
public class BaseResponse<T> {
    /**
     * 响应状态码（自定义：200成功，非200失败）
     */
    private int code;

    /**
     * 响应提示信息
     */
    private String message;

    /**
     * 响应数据体（成功时返回业务数据，失败时可返回null或错误详情）
     */
    private T data;

    // 无参构造器（便于反射和序列化）
    public BaseResponse() {
    }

    // 全参构造器（便于快速构建响应对象）
    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 静态快捷方法：构建成功响应（无需手动传入200状态码，简化调用）
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, "操作成功", data);
    }

    // 重载：构建带提示信息的成功响应
    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(200, message, data);
    }

    // 静态快捷方法：构建失败响应
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code, message, null);
    }
}