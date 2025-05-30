package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.tag.ImageTagDetailResponseDTO;
import com.muzixiao2.bakabooru.entity.ImageTag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageTagMapper {
    default ImageTagDetailResponseDTO toResponseDTO(List<ImageTag> tags) {
        ImageTagDetailResponseDTO dto = new ImageTagDetailResponseDTO();

        dto.setArtist(tags.stream()
                .filter(tag -> "artist".equals(tag.getType()))
                .map(ImageTag::getName)
                .toList());

        dto.setCharacter(tags.stream()
                .filter(tag -> "character".equals(tag.getType()))
                .map(ImageTag::getName)
                .toList());

        dto.setCopyright(tags.stream()
                .filter(tag -> "copyright".equals(tag.getType()))
                .map(ImageTag::getName)
                .toList());

        dto.setGeneral(tags.stream()
                .filter(tag -> "general".equals(tag.getType()))
                .map(ImageTag::getName)
                .toList());

        dto.setMeta(tags.stream()
                .filter(tag -> "meta".equals(tag.getType()))
                .map(ImageTag::getName)
                .toList());

        return dto;
    }
}
