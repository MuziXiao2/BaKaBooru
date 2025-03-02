package com.xiao2.bakabooru.converter;

import com.xiao2.bakabooru.dto.ImageRequestDTO;
import com.xiao2.bakabooru.dto.ImageResponseDTO;
import com.xiao2.bakabooru.model.Image;

public class ImageConverter {
    public static Image toImage(ImageRequestDTO imageRequestDTO) {
        Image image = new Image();

        image.setTitle(imageRequestDTO.getTitle());
        image.setUrl(imageRequestDTO.getUrl());
        image.setSize(imageRequestDTO.getSize());
        image.setAtlasId(imageRequestDTO.getAtlasId());

        return image;
    }

    public static ImageResponseDTO toImageResponseDTO(Image image) {
        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();

        imageResponseDTO.setTitle(image.getTitle());
        imageResponseDTO.setUrl(image.getUrl());
        imageResponseDTO.setSn(image.getSn());
        imageResponseDTO.setAtlasId(image.getAtlasId());
        imageResponseDTO.setSize(image.getSize());

        return imageResponseDTO;
    }
}
