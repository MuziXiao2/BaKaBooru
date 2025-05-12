package com.muzixiao2.bakabooru.hub.mapper;

import com.muzixiao2.bakabooru.hub.dto.source.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.SourceMeta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SourceMapper {
    SourceMeta toMeta(SourceReferenceDTO sourceSyncDTO);

    @Mapping(source = "sourceMeta.uuid", target = "uuid")
    @Mapping(source = "sourceMeta.name", target = "name", defaultExpression = "java(sourceRemoteDTO.getDefaultName())")
    @Mapping(source = "sourceMeta.url", target = "url")
    @Mapping(source = "sourceRemoteDTO.creator", target = "creator")
    @Mapping(source = "sourceRemoteDTO.updatedAt", target = "updatedAt")
    @Mapping(source = "sourceRemoteDTO.createdAt", target = "createdAt")
    @Mapping(source = "sourceMeta.addedAt", target = "addedAt")
    SourceResponseDTO toSourceResponseDTO(SourceMeta sourceMeta, SourceRemoteDTO sourceRemoteDTO);

}
