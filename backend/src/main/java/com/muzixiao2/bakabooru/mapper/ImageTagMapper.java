package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.tag.ImageTagDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.tag.ImageTagResponseDTO;
import com.muzixiao2.bakabooru.entity.ImageTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageTagMapper {

    @Mapping(target = "type", source = "type", qualifiedByName = "tagTypeToString")
    ImageTagDetailResponseDTO toResponseDTO(ImageTag tag);

    @Named("tagTypeToString")
    static String tagTypeToString(ImageTag.TagType tagType) {
        return tagType == null ? null : tagType.name().toLowerCase();
    }

    default ImageTagResponseDTO toResponseDTO(List<ImageTag> tags) {
        ImageTagResponseDTO imageTagResponseDTO = new ImageTagResponseDTO();

        imageTagResponseDTO.setGeneral(tags.stream()
                .filter(tag -> tag.getType() == ImageTag.TagType.GENERAL)
                .map(ImageTag::getName)
                .toList());

        imageTagResponseDTO.setCharacter(tags.stream()
                .filter(tag -> tag.getType() == ImageTag.TagType.CHARACTER)
                .map(ImageTag::getName)
                .toList());

        imageTagResponseDTO.setCopyright(tags.stream()
                .filter(tag -> tag.getType() == ImageTag.TagType.COPYRIGHT)
                .map(ImageTag::getName)
                .toList());

        imageTagResponseDTO.setArtist(tags.stream()
                .filter(tag -> tag.getType() == ImageTag.TagType.ARTIST)
                .map(ImageTag::getName)
                .toList());

        imageTagResponseDTO.setMeta(tags.stream()
                .filter(tag -> tag.getType() == ImageTag.TagType.META)
                .map(ImageTag::getName)
                .toList());

        return imageTagResponseDTO;
    }
}
