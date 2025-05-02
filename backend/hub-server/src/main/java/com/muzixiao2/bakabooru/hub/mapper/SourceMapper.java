package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.frontend.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.frontend.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import com.muzixiao2.bakabooru.hub.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    Source toEntity(SourceReferenceDTO dto);

    Source toEntity(SourceSyncDTO dto);

    SourceResponseDTO toResponseDTO(Source dto);
}
