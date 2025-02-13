package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Atlas;
import lombok.Data;

@Data
public class AtlasResponseDTO {
    private Long id;

    private String name;
    private String coverUrl;

    private Long sourceId;

    public AtlasResponseDTO(Atlas atlas) {
        this.id = atlas.getId();

        this.name = atlas.getName();
        this.coverUrl = atlas.getCoverUrl();

        this.sourceId = atlas.getSourceId();
    }
}
