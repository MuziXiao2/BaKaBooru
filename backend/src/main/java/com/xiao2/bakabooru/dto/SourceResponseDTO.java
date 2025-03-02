package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long groupId;
    private Instant createdAt;
    private Instant updatedAt;
}
