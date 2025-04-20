package com.xiao2.bakabooru.backenddata.converter;

import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.model.Source;
import com.xiao2.bakabooru.backenddata.model.Group;
import jakarta.persistence.PrePersist;

import java.time.Instant;

public class SourceConverter {

    public static Source toSource(SourceReferenceDTO sourceReferenceDTO) {
        Source source = new Source();
        source.setName(sourceReferenceDTO.getName());
        source.setUrl(sourceReferenceDTO.getUrl());
        source.setGroupId(sourceReferenceDTO.getGroupId());
        return source;
    }


    public static SourceResponseDTO toSourceResponseDTO(Source source) {
        SourceResponseDTO sourceResponseDTO = new SourceResponseDTO();

        sourceResponseDTO.setId(source.getId());
        sourceResponseDTO.setGroupId(source.getGroupId());
        sourceResponseDTO.setName(source.getName());
        sourceResponseDTO.setUrl(source.getUrl());
        sourceResponseDTO.setAddedAt(source.getAddedAt());

        return sourceResponseDTO;
    }

}
