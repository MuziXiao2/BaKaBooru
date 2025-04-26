package com.muzixiao2.bakabooru.source.mapper;

import com.muzixiao2.bakabooru.source.dto.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.source.dto.ImageResponseDTO;
import com.muzixiao2.bakabooru.source.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toEntity(ImageUploadResponseDTO imageUploadResponseDTO);

    ImageResponseDTO toResponseDTO(Image image);

    ImageUploadResponseDTO toUploadResponseDTO(Image image);
}
