package com.xiao2.bakabooru.backenddata.mapper;

import com.xiao2.bakabooru.backenddata.dto.AtlasRequestDTO;
import com.xiao2.bakabooru.backenddata.dto.AtlasResponseDTO;
import com.xiao2.bakabooru.backenddata.entity.Atlas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtlasMapper {

    Atlas toEntity(AtlasRequestDTO atlasRequestDTO);

    @Mapping(source = "source.id", target = "sourceId")
    AtlasResponseDTO toResponseDTO(Atlas atlas);
}
