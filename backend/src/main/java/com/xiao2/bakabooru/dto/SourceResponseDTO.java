package com.xiao2.bakabooru.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class SourceResponseDTO {
    private Long id;
    private Long groupId;

    private String name;
    private String type;
    private String url;
    private Instant createdAt;
    private Instant updatedAt;
}
