package com.xiao2.bakabooru.hub.mapper;

import com.xiao2.bakabooru.hub.dto.SourceReferenceDTO;
import com.xiao2.bakabooru.hub.dto.SourceResponseDTO;
import com.xiao2.bakabooru.hub.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    Source toEntity(SourceReferenceDTO dto);
    SourceResponseDTO toResponseDTO(Source dto);
}
