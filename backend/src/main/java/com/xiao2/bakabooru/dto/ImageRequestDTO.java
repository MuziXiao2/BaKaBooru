package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Image;
import lombok.Data;

@Data
public class ImageRequestDTO {
    private String title;
    private String url;
    private Long atlasId;

    public Image toImage() {
        Image image = new Image();

        image.setTitle(title);
        image.setUrl(url);
        image.setAtlasId(atlasId);

        return image;
    }
}
