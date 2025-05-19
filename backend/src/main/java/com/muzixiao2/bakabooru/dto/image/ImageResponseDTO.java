package com.muzixiao2.bakabooru.dto.image;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.Instant;

@Data
@NoArgsConstructor
public class ImageResponseDTO {
    private String uuid;
    private String title;
    private String creator;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private List<ImageFileResponseDTO> files;
}
