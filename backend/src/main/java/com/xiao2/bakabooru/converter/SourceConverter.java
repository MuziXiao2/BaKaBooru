package com.xiao2.bakabooru.converter;

import com.xiao2.bakabooru.dto.SourceRequestDTO;
import com.xiao2.bakabooru.dto.SourceResponseDTO;
import com.xiao2.bakabooru.model.Source;
import jakarta.persistence.PrePersist;

import java.time.Instant;

public class SourceConverter {
    @PrePersist
    public static Source toSource(SourceRequestDTO sourceRequestDTO) {
        Source source = new Source();

        source.setName(sourceRequestDTO.getName());
        source.setType(sourceRequestDTO.getType());
        source.setUrl(sourceRequestDTO.getUrl());

        Instant now = Instant.now();
        source.setCreateAt(now);
        source.setUpdatedAt(now);

        return source;
    }

    public static SourceResponseDTO toSourceResponseDTO(Source source) {
        SourceResponseDTO sourceResponseDTO = new SourceResponseDTO();

        sourceResponseDTO.setId(source.getId());
        sourceResponseDTO.setName(source.getName());
        sourceResponseDTO.setType(source.getType());
        sourceResponseDTO.setUrl(source.getUrl());
        sourceResponseDTO.setCreateAt(source.getCreateAt());
        sourceResponseDTO.setUpdatedAt(source.getUpdatedAt());

        return sourceResponseDTO;
    }
}
