package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Image;
import lombok.Data;


@Data
public class ImageResponseDTO {
    private String title;
    private String url;
    private Long sn;
    private Long atlasId;

    public ImageResponseDTO(Image image) {
        this.title = image.getTitle();
        this.url = image.getUrl();
        this.sn = image.getSn();
        this.atlasId = image.getAtlasId();
    }
}
