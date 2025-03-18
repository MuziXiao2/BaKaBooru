package com.xiao2.bakabooru.backenddata.converter;

import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.model.Source;
import com.xiao2.bakabooru.backenddata.model.Group;
import jakarta.persistence.PrePersist;

import java.time.Instant;

public class GroupConverter {

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
