package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AtlasResponseDTO {
    private Long id;
    private String title;
    private String coverUrl;
    private String creator;
    private Instant createdAt;
    private Instant updatedAt;
}
