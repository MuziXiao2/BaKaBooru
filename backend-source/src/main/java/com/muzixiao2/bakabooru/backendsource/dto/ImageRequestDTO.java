package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;

@Data
public class ImageRequestDTO {
    private String uuid;
    private String title;
    private Long size;
    private String extension;
    private Long atlasId;
}