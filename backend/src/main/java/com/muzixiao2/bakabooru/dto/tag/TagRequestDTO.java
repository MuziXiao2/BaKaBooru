package com.muzixiao2.bakabooru.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "创建标签请求参数")
public class TagRequestDTO {
    @Schema(description = "标签名称")
    private String name;
    @Schema(description = "标签类型")
    private String type;
    @Schema(description = "标签解释")
    private String description;
}
