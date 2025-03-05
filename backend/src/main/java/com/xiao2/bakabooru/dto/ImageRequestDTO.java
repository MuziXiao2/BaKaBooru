package com.xiao2.bakabooru.dto;

import lombok.Data;

@Data
public class ImageRequestDTO {
    private String title;
    private String url;
    private Long size;
    private Long atlasId;
}