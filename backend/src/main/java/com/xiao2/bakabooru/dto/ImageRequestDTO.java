package com.xiao2.bakabooru.dto;

import lombok.Data;

@Data
public class ImageRequestDTO {
    private String title;
    private String uuid;
    private String extension;
    private Long size;
    private Long atlasId;
}