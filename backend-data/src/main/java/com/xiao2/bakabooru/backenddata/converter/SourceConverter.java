package com.xiao2.bakabooru.backenddata.converter;

import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.model.Source;
import com.xiao2.bakabooru.backenddata.model.Group;
import jakarta.persistence.PrePersist;

import java.time.Instant;

public class SourceConverter {

    public static Source toSource(SourceInfo sourceInfo) {
        Source source = new Source();
        source.setName(sourceInfo.getName());
        source.setUrl(sourceInfo.getUrl());
        source.setCreator(sourceInfo.getCreator());
        source.setCreatedAt(Instant.parse(sourceInfo.getCreatedAt()));
        source.setUpdatedAt(Instant.parse(sourceInfo.getUpdatedAt()));

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

    public static Group toGroup(GroupRequestDTO groupRequestDTO) {
        Group group = new Group();
        group.setName(groupRequestDTO.getName());
        return group;
    }

    public static GroupResponseDTO toGroupResponseDTO(Group group) {
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        groupResponseDTO.setId(group.getId());
        groupResponseDTO.setSn(group.getSn());
        groupResponseDTO.setName(group.getName());
        return groupResponseDTO;
    }
}
