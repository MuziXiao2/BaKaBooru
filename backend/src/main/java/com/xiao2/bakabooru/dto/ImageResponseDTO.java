package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Image;
import lombok.Data;

@Data
public class ImageResponseDTO {
    private String name;
    private String fileUrl;

    private Long position;
    private Long atlasId;

    public ImageResponseDTO(Image image) {
        this.name = image.getName();
        this.fileUrl = image.getFileUrl();

        this.position = image.getPosition();
        this.atlasId = image.getAtlasId();
    }
}
