package com.xiao2.bakabooru.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageResponseDTO {
    private String title;
    private String uuid;
    private String extension;
    private String url;
    private Long sn;
    private Long size;
    private Long atlasId;
}
