package com.muzixiao2.bakabooru.source.mapper;

import com.muzixiao2.bakabooru.source.dto.image.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.source.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.source.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "referenceCount", ignore = true)
    @Mapping(target = "uploadedAt", ignore = true)
    Image toEntity(ImageUploadResponseDTO imageUploadResponseDTO);

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "url", ignore = true)
    ImageResponseDTO toResponseDTO(Image image);

    ImageUploadResponseDTO toUploadResponseDTO(Image image);
}
