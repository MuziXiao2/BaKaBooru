package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;

@Data
public class ImageRequestDTO {
    private String uuid;
    private Long atlasId;

    private String title;
    private String extension;
    private Long size;
}