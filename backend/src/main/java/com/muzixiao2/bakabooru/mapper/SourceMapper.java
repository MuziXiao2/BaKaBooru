package com.muzixiao2.bakabooru.mapper;

import com.muzixiao2.bakabooru.config.SourceConfig;
import com.muzixiao2.bakabooru.dto.source.SourceResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    SourceResponseDTO toResponseDTO(SourceConfig sourceConfig);
}
