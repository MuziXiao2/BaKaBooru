package com.muzixiao2.bakabooru.dto.image;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageQueryRequestDTO {
    @Schema(description = "标题关键字（模糊匹配）")
    private String keyword;

    @Schema(description = "标签 ID 列表，例如 [\"character:john\", \"scene:beach\"]")
    private List<String> tags;

    @Schema(description = "排序字段", example = "title", defaultValue = "title")
    private String sortBy = "title";

    @Schema(description = "顺序方向", example = "ASC", defaultValue = "ASC")
    private String sortDirection = "ASC";

    @Schema(description = "页码，从 1 开始", example = "1", defaultValue = "1")
    private Integer page = 1;

    @Schema(description = "每页记录数", example = "10", defaultValue = "10")
    private Integer size = 10;
}
