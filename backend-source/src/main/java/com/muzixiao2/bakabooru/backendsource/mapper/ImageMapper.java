package com.muzixiao2.bakabooru.backendsource.mapper;

import com.muzixiao2.bakabooru.backendsource.dto.ImageRequestDTO;
import com.muzixiao2.bakabooru.backendsource.dto.ImageResponseDTO;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toEntity(ImageRequestDTO imageRequestDTO);

    @Mapping(target = "title", ignore = true)
    @Mapping(target = "url", ignore = true)
    ImageResponseDTO toResponseDTO(Image image);
}
