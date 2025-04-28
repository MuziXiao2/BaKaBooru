package com.muzixiao2.bakabooru.hub.dto.sync;

import lombok.Data;

import java.time.Instant;

@Data
public class SourceSyncDTO {
    private String url;
    private String defaultName;
    private String creator;
    private String createdAt;
    private Instant updatedAt;
}
