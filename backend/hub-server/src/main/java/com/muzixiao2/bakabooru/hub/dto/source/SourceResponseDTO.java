package com.muzixiao2.bakabooru.hub.dto.source;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@Schema(description = "图源信息响应体")
public class SourceResponseDTO {
    @Schema(description = "ID")
    private String id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "服务器地址")
    private String url;
    @Schema(description = "创建者")
    private String creator;
    @Schema(description = "更新时间")
    private Instant updatedAt;
    @Schema(description = "创建时间")
    private Instant createdAt;
    @Schema(description = "添加时间")
    private Instant addedAt;
}
