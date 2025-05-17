package com.muzixiao2.bakabooru.dto.source;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Data
@NoArgsConstructor
@Schema(description = "图源信息响应体")
public class SourceResponseDTO {
    @Schema(description = "名称")
    private String name;
    @Schema(description = "服务器地址")
    private String url;
    @Schema(description = "OSS地址")
    private String ossUrl;
    @Schema(description = "创建者")
    private String creator;
    @Schema(description = "创建时间")
    private Instant createdAt;
}
