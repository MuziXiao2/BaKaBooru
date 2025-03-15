package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AtlasResponseDTO {
    private Long id;
    private String title;
    private String coverUrl;
    private String creator;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Long sourceId;
}
