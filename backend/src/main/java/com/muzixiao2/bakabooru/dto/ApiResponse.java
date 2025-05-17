package com.muzixiao2.bakabooru.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "统一的 API 响应结构")
public class ApiResponse<T> {
    @Schema(description = "请求是否成功", example = "true")
    private boolean success;

    @Schema(description = "错误消息或提示", example = "操作成功")
    private String message;

    @Schema(description = "响应数据")
    private T data;

    @Schema(description = "错误码（可选）", example = "SOURCE_NOT_FOUND")
    private String errorCode;

    @Schema(description = "时间戳", example = "1714819200000")
    private long timestamp;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Request successful", data, null, System.currentTimeMillis());
    }

    public static <T> ApiResponse<T> error(String errorCode, String message) {
        return new ApiResponse<>(false, message, null, errorCode, System.currentTimeMillis());
    }
}
