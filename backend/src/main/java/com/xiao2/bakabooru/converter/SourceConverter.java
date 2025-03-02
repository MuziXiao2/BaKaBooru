package com.xiao2.bakabooru.converter;

import com.xiao2.bakabooru.dto.SourceGroupRequestDTO;
import com.xiao2.bakabooru.dto.SourceGroupResponseDTO;
import com.xiao2.bakabooru.dto.SourceRequestDTO;
import com.xiao2.bakabooru.dto.SourceResponseDTO;
import com.xiao2.bakabooru.model.Source;
import com.xiao2.bakabooru.model.SourceGroup;
import jakarta.persistence.PrePersist;

import java.time.Instant;

public class SourceConverter {
    @PrePersist
    public static Source toSource(SourceRequestDTO sourceRequestDTO) {
        Source source = new Source();

        source.setName(sourceRequestDTO.getName());
        source.setType(sourceRequestDTO.getType());
        source.setUrl(sourceRequestDTO.getUrl());
        source.setGroupId(sourceRequestDTO.getGroupId());

        Instant now = Instant.now();
        source.setCreatedAt(now);
        source.setUpdatedAt(now);

        return source;
    }

    public static SourceResponseDTO toSourceResponseDTO(Source source) {
        SourceResponseDTO sourceResponseDTO = new SourceResponseDTO();

        sourceResponseDTO.setId(source.getId());
        sourceResponseDTO.setName(source.getName());
        sourceResponseDTO.setType(source.getType());
        sourceResponseDTO.setUrl(source.getUrl());
        sourceResponseDTO.setGroupId(source.getGroupId());
        sourceResponseDTO.setCreatedAt(source.getCreatedAt());
        sourceResponseDTO.setUpdatedAt(source.getUpdatedAt());

        return sourceResponseDTO;
    }

    public static SourceGroup toSourceGroup(SourceGroupRequestDTO groupRequestDTO) {
        SourceGroup group = new SourceGroup();
        group.setName(groupRequestDTO.getName());
        return group;
    }

    public static SourceGroupResponseDTO toSourceGroupResponseDTO(SourceGroup group) {
        SourceGroupResponseDTO sourceGroupResponseDTO = new SourceGroupResponseDTO();
        sourceGroupResponseDTO.setId(group.getId());
        sourceGroupResponseDTO.setSn(group.getSn());
        sourceGroupResponseDTO.setName(group.getName());
        return sourceGroupResponseDTO;
    }
}
