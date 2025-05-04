package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtlasMapper {
    AtlasResponseDTO toResponseDTO(AtlasRemoteDTO atlasRemoteDTO);
}
