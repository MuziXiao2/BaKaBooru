package com.xiao2.bakabooru.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用响应对象，统一格式用于所有请求的响应
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> {

    private boolean success;  // 操作是否成功
    private String message;   // 返回的操作消息
    private T data;           // 泛型字段，返回的数据
    private int code;         // 状态码，200为成功，其他为错误类型

    // 成功时创建响应
    public static <T> ResponseResult<T> success(T data, String message) {
        return new ResponseResult<>(true, message, data, 200);
    }

    // 错误时创建响应
    public static <T> ResponseResult<T> error(String message, int code) {
        return new ResponseResult<>(false, message, null, code);
    }
}
