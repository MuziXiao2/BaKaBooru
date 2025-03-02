package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageRequestDTO {
    private String title;
    private String url;
    private Long size;
    @JsonProperty("atlas_id")
    private Long atlasId;
}