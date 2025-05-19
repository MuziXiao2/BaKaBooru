package com.muzixiao2.bakabooru.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "图片查询结果")
public class ImageQueryResponseDTO {
    @Schema(description = "图片uuid")
    private String uuid;
    @Schema(description = "图片标题")
    private String title;
    @Schema(description = "图片封面hash")
    private String coverHash;
}
