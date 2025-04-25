package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AtlasResponseDTO {
    private String uuid;
    private String title;
    private String creator;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}
