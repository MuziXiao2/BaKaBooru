package com.xiao2.bakabooru.backenddata.mapper;

import com.xiao2.bakabooru.backenddata.dto.GroupResponseDTO;
import com.xiao2.bakabooru.backenddata.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupResponseDTO toResponseDTO(Group dto);
}
