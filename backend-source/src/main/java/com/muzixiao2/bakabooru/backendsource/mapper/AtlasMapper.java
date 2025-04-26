package com.muzixiao2.bakabooru.backendsource.mapper;

import com.muzixiao2.bakabooru.backendsource.dto.AtlasRequestDTO;
import com.muzixiao2.bakabooru.backendsource.dto.AtlasResponseDTO;
import com.muzixiao2.bakabooru.backendsource.entity.Atlas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtlasMapper {
    @Mapping(target = "coverHash", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "uuid", expression = "java(java.util.UUID.randomUUID().toString())")
    Atlas toEntity(AtlasRequestDTO atlasRequestDTO);

    @Mapping(target = "createdAt", ignore = true)
    AtlasResponseDTO toResponseDTO(Atlas atlas);
}
