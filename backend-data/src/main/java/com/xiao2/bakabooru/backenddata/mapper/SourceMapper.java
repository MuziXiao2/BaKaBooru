package com.xiao2.bakabooru.backenddata.mapper;

import com.xiao2.bakabooru.backenddata.dto.SourceReferenceDTO;
import com.xiao2.bakabooru.backenddata.dto.SourceResponseDTO;
import com.xiao2.bakabooru.backenddata.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    Source toEntity(SourceReferenceDTO dto);
    SourceResponseDTO toResponseDTO(Source dto);
}
