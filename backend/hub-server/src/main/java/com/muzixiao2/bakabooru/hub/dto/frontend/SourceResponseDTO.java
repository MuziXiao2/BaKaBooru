package com.muzixiao2.bakabooru.hub.dto.frontend;

import lombok.Data;

import java.time.Instant;

@Data
public class SourceResponseDTO {
    private String name;
    private String url;
    private String groupName;
    private Instant addedAt;
    private Instant updatedAt;
}
