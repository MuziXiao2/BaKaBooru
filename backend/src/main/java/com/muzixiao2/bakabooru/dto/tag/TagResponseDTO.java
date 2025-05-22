package com.muzixiao2.bakabooru.dto.tag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TagResponseDTO {
    private Long id;
    private String typeName;
    private String variant;
    private String description;
}
