package com.muzixiao2.bakabooru.dto.image;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ImageResponseDTO {
    private String uuid;
    private String sourceUuid;
    private String title;
    private String coverHash;
    private String creator;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

}
