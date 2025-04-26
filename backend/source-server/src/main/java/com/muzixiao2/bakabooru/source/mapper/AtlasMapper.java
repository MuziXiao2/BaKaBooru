package com.muzixiao2.bakabooru.source.mapper;

import com.muzixiao2.bakabooru.source.dto.AtlasRequestDTO;
import com.muzixiao2.bakabooru.source.dto.AtlasResponseDTO;
import com.muzixiao2.bakabooru.source.entity.Atlas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtlasMapper {
    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID().toString())")
    Atlas toEntity(AtlasRequestDTO atlasRequestDTO);

    AtlasResponseDTO toResponseDTO(Atlas atlas);
}
