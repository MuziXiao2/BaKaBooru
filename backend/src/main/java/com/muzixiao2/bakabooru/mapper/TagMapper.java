package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.tag.TagResponseDTO;
import com.muzixiao2.bakabooru.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mapping(target = "typeName", source = "type.name")
    TagResponseDTO toResponseDTO(Tag tag);
}
