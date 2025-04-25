package com.xiao2.bakabooru.backenddata.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class AtlasRequestDTO {
    private Long sourceId;
    private String title;
    private String creator;
    private Instant createAt;
    private Instant updatedAt;
}
