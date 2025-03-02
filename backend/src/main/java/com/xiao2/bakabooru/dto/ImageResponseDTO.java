package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageResponseDTO {
    private String title;
    private String url;
    private Long sn;
    private Long size;
    @JsonProperty("atlas_id")
    private Long atlasId;
}
