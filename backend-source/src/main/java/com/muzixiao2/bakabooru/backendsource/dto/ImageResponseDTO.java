package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageResponseDTO {
    private Long id;
    private Long sn;
    private Long atlasId;
    private String uuid;

    private String title;
    private String extension;
    private Long size;
}
