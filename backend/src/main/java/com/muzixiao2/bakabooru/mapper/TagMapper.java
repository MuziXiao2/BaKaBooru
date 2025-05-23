package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDetailResponseDTO toResponseDTO(Tag tag);

    Tag toEntity(TagRequestDTO tagRequestDTO);
}
