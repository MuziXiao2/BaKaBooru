package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "type", source = "type.name")
    TagDetailResponseDTO toResponseDTO(Tag tag);

    @Mapping(target = "type", expression = "java(TagType.fromName(tagRequestDTO.getType()))")
    Tag toEntity(TagRequestDTO tagRequestDTO);
}
