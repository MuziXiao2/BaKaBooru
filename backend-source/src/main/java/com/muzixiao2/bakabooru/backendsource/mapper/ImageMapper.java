package com.muzixiao2.bakabooru.backendsource.mapper;

import com.muzixiao2.bakabooru.backendsource.dto.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.backendsource.dto.ImageResponseDTO;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toEntity(ImageUploadResponseDTO imageUploadResponseDTO);

    ImageResponseDTO toResponseDTO(Image image);

    ImageUploadResponseDTO toUploadResponseDTO(Image image);
}
