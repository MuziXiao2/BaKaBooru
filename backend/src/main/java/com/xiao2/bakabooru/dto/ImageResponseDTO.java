package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Image;
import lombok.Data;

@Data
public class ImageResponseDTO {
    private Long id;
    private String name;
    private String url;

    public ImageResponseDTO(Image image) {
        this.id = image.getId();
        this.name = image.getName();
        this.url = image.getUrl();
    }
}
