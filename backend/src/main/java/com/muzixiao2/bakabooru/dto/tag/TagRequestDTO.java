package com.muzixiao2.bakabooru.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "添加标签请求参数")
public class TagRequestDTO {
    @Schema(description = "标签id")
    private String name;
    @Schema(description = "标签标识")
    private String variant;
    @Schema(description = "标签类型")
    private String type;
}
