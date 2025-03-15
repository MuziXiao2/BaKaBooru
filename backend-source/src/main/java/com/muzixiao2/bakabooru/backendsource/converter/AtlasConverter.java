package com.muzixiao2.bakabooru.backendsource.converter;

import com.muzixiao2.bakabooru.backendsource.dto.AtlasRequestDTO;
import com.muzixiao2.bakabooru.backendsource.dto.AtlasResponseDTO;
import com.muzixiao2.bakabooru.backendsource.model.Atlas;
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
