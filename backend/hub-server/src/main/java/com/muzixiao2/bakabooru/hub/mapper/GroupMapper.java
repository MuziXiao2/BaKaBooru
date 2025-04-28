package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.frontend.GroupResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupResponseDTO toResponseDTO(Group dto);
}
