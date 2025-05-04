package com.muzixiao2.bakabooru.hub.dto.source;

import lombok.Data;

import java.time.Instant;

@Data
public class SourceDetailDTO {
    private Long id;
    private String name;
    private String defaultName;
    private String url;
    private String creator;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant addedAt;
}
