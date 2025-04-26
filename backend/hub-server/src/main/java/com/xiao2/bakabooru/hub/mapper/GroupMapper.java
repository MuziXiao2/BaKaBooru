package com.xiao2.bakabooru.hub.mapper;

import com.xiao2.bakabooru.hub.dto.GroupResponseDTO;
import com.xiao2.bakabooru.hub.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupResponseDTO toResponseDTO(Group dto);
}
