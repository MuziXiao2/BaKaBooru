package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.image.ImageRequestDTO;
import com.muzixiao2.bakabooru.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.ImageFileUploadResponseDTO;
import com.muzixiao2.bakabooru.dto.image.ImageFileResponseDTO;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.entity.ImageImageFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Image toEntity(ImageRequestDTO imageRequestDTO);

    ImageFile toEntity(ImageFileUploadResponseDTO imageFileUploadResponseDTO);

    @Mapping(target = "hash", source = "imageFile.hash")
    @Mapping(target = "type", source = "imageFile.type")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "width", source = "imageFile.width")
    @Mapping(target = "height", source = "imageFile.height")
    @Mapping(target = "size", source = "imageFile.size")
    ImageFileResponseDTO toResponseDTO(ImageImageFile imageImageFile);

    ImageResponseDTO toResponseDTO(Image atlas);


}
