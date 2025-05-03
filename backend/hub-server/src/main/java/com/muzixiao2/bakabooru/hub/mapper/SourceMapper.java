package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.frontend.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.frontend.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    Source toEntity(SourceReferenceDTO sourceSyncDTO);

    SourceResponseDTO toResponseDTO(Source dto);
}
