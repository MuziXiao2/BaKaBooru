package com.muzixiao2.bakabooru.hub.dto.source;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@Schema(description = "图源信息响应体")
public class SourceResponseDTO {
    @Schema(description = "图源ID", example = "1")
    private Long id;
    @Schema(description = "图源名称", example = "local-source")
    private String name;
    @Schema(description = "图源服务器地址", example = "http://localhost:8080")
    private String url;
    @Schema(description = "图源创建者", example = "test")
    private String creator;
    @Schema(description = "图源更新时间")
    private Instant updatedAt;
    @Schema(description = "图源创建时间")
    private Instant createdAt;
    @Schema(description = "图源添加时间")
    private Instant addedAt;
}
