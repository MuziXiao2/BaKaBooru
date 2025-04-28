package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.frontend.AtlasRequestDTO;
import com.muzixiao2.bakabooru.hub.dto.frontend.AtlasResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.Atlas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtlasMapper {

    Atlas toEntity(AtlasRequestDTO atlasRequestDTO);

    @Mapping(source = "source.id", target = "sourceId")
    AtlasResponseDTO toResponseDTO(Atlas atlas);
}
