package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtlasMapper {
    @Mapping(target = "sourceUuid", source = "sourceUuid")
    AtlasResponseDTO toResponseDTO(String sourceUuid,AtlasRemoteDTO atlasRemoteDTO);
}
