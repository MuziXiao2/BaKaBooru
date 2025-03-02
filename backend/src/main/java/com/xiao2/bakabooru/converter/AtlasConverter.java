package com.xiao2.bakabooru.converter;

import com.xiao2.bakabooru.dto.AtlasRequestDTO;
import com.xiao2.bakabooru.dto.AtlasResponseDTO;
import com.xiao2.bakabooru.model.Atlas;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class AtlasConverter {
    @PrePersist
    public static Atlas toAtlas(AtlasRequestDTO atlasRequestDTO) {

        Atlas atlas = new Atlas();

        atlas.setTitle(atlasRequestDTO.getTitle());
        atlas.setCreator(atlasRequestDTO.getCreator());
        atlas.setSourceId(atlasRequestDTO.getSourceId());

        LocalDateTime now = LocalDateTime.now();
        atlas.setCreateAt(now);
        atlas.setUpdatedAt(now);

        return atlas;
    }

    public static AtlasResponseDTO toAtlasResponseDTO(Atlas atlas) {
        AtlasResponseDTO atlasResponseDTO = new AtlasResponseDTO();

        atlasResponseDTO.setId(atlas.getId());
        atlasResponseDTO.setTitle(atlas.getTitle());
        atlasResponseDTO.setCoverUrl(atlas.getCoverUrl());
        atlasResponseDTO.setCreator(atlas.getCreator());
        atlasResponseDTO.setCreateAt(atlas.getCreateAt());
        atlasResponseDTO.setUpdatedAt(atlas.getUpdatedAt());
        atlasResponseDTO.setSourceId(atlas.getSourceId());

        return atlasResponseDTO;
    }
}
