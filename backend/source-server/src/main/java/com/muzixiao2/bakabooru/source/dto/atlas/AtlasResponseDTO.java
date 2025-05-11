package com.muzixiao2.bakabooru.source.dto.atlas;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class AtlasResponseDTO {
    private String uuid;
    private String title;
    private String coverHash;
    private String creator;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}
