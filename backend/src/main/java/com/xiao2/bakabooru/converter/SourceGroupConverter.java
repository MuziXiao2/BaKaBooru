package com.xiao2.bakabooru.converter;


import com.xiao2.bakabooru.dto.SourceGroupRequestDTO;
import com.xiao2.bakabooru.dto.SourceGroupResponseDTO;
import com.xiao2.bakabooru.model.SourceGroup;

public class SourceGroupConverter {

    public static SourceGroup toSourceGroup(SourceGroupRequestDTO groupRequestDTO) {
        SourceGroup group = new SourceGroup();
        group.setName(groupRequestDTO.getName());
        return group;
    }

    public static SourceGroupResponseDTO toSourceGroupResponseDTO(SourceGroup group) {
        SourceGroupResponseDTO sourceGroupResponseDTO = new SourceGroupResponseDTO();
        sourceGroupResponseDTO.setSn(group.getSn());
        sourceGroupResponseDTO.setName(group.getName());
        return sourceGroupResponseDTO;
    }
}
