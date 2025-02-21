package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiao2.bakabooru.model.Image;
import lombok.Data;

@Data
public class ImageRequestDTO {
    private String title;
    private String url;
    @JsonProperty("atlas_id")
    private Long atlasId;
}
