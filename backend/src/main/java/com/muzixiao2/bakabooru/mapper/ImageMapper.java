package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.file.FileDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.file.FileUploadResponseDTO;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.entity.File;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Image toEntity(ImageRequestDTO imageRequestDTO);

    File toEntity(FileUploadResponseDTO imageFileUploadResponseDTO);

    @Mapping(target = "hash", source = "file.hash")
    @Mapping(target = "type", source = "file.type")
    @Mapping(target = "fileName", source = "fileName")
    @Mapping(target = "width", source = "file.width")
    @Mapping(target = "height", source = "file.height")
    @Mapping(target = "size", source = "file.size")
    FileDetailResponseDTO toResponseDTO(ImageFile imageFile);

    ImageDetailResponseDTO toResponseDTO(Image image);

    @Mapping(target = "coverHash", ignore = true)
    ImageQueryResponseDTO toQueryResponseDTO(Image image);
}
