package com.muzixiao2.bakabooru.dto.image;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.Instant;

@Data
@NoArgsConstructor
public class ImageDetailResponseDTO {
    private String uuid;
    private String title;
    private String creator;
    private String description;
    private Long viewCount;
    private Instant createdAt;
    private Instant updatedAt;
}
