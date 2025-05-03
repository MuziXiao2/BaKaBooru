package com.muzixiao2.bakabooru.hub.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "图源注册请求参数")
public class SourceReferenceDTO {
    @Schema(description = "图源名称", example = "local-source")
    private String name;
    @Schema(description = "图源服务器地址", example = "http://localhost:8080")
    private String url;
}
