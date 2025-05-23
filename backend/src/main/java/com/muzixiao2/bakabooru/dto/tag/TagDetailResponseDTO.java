package com.muzixiao2.bakabooru.dto.tag;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TagDetailResponseDTO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private String count;
}
