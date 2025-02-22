package com.xiao2.bakabooru.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class SourceResponseDTO {
    private Long id;
    private String name;
    private String type;
    private String url;
    private Instant createAt;
    private Instant updatedAt;

}
