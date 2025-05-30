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

    @Schema(description = "标签条件，支持按类型过滤多个标签")
    private List<String> tags;

    @Schema(description = "排序字段，可选：title, createdAt, updatedAt", example = "updatedAt", defaultValue = "updatedAt")
    private String sortBy = "updatedAt";

    @Schema(description = "排序方向，可选：ASC 或 DESC", example = "DESC", defaultValue = "DESC")
    private String sortDirection = "DESC";

    @Schema(description = "页码（从 1 开始）", example = "1", defaultValue = "1", minimum = "1")
    private Integer page = 1;

    @Schema(description = "每页数量", example = "20", defaultValue = "20", minimum = "1")
    private Integer size = 20;
}

