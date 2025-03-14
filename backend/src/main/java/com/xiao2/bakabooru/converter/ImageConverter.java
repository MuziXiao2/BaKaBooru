package com.xiao2.bakabooru.converter;

import com.xiao2.bakabooru.dto.ImageRequestDTO;
import com.xiao2.bakabooru.dto.ImageResponseDTO;
import com.xiao2.bakabooru.model.Image;

public class ImageConverter {
    public static Image toImage(ImageRequestDTO imageRequestDTO) {
        Image image = new Image();

        image.setAtlasId(imageRequestDTO.getAtlasId());
        image.setUuid(imageRequestDTO.getUuid());

        image.setTitle(imageRequestDTO.getTitle());
        image.setExtension(imageRequestDTO.getExtension());
        image.setSize(imageRequestDTO.getSize());

        return image;
    }

    public static ImageResponseDTO toImageResponseDTO(Image image) {
        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();

        imageResponseDTO.setId(image.getId());
        imageResponseDTO.setSn(image.getSn());
        imageResponseDTO.setAtlasId(image.getAtlasId());
        imageResponseDTO.setUuid(image.getUuid());

        imageResponseDTO.setTitle(image.getTitle());
        imageResponseDTO.setExtension(image.getExtension());
        imageResponseDTO.setSize(image.getSize());

        return imageResponseDTO;
    }
}
