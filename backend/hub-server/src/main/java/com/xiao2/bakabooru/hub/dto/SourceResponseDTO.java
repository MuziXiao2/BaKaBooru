package com.xiao2.bakabooru.hub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class SourceResponseDTO {
    private Long id;
    private String name;
    private String url;
    private String groupName;
    private Instant addedAt;
}
