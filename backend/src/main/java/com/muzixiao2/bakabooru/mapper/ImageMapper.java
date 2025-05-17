package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.image.ImageRequestDTO;
import com.muzixiao2.bakabooru.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.ImageFileUploadResponseDTO;
import com.muzixiao2.bakabooru.dto.image.ImageFileResponseDTO;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "referenceCount", ignore = true)
    @Mapping(target = "uploadedAt", ignore = true)
    ImageFile toEntity(ImageFileUploadResponseDTO imageFileUploadResponseDTO);

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "url", ignore = true)
    ImageFileResponseDTO toResponseDTO(ImageFile imageFile);

    ImageFileUploadResponseDTO toUploadResponseDTO(ImageFile imageFile);

    ImageResponseDTO toResponseDTO(Image atlas);

    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID().toString())")
    Image toEntity(ImageRequestDTO imageRequestDTO);

}
