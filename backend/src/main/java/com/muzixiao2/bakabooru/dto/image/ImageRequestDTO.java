package com.muzixiao2.bakabooru.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "添加图集请求参数")
public class ImageRequestDTO {
    @Schema(description = "图集标题")
    private String title;
    @Schema(description = "图集创建者")
    private String creator;
    @Schema(description = "图集描述")
    private String description;
}
