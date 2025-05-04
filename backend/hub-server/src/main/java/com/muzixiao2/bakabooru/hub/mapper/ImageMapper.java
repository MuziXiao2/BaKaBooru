package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.image.ImageRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageUploadRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageUploadResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageResponseDTO toResponseDTO(ImageRemoteDTO imageRemoteDTO);
    ImageUploadResponseDTO toResponseDTO(ImageUploadRemoteDTO imageUploadRemoteDTO);
}
