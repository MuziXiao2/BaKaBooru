package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiao2.bakabooru.model.Atlas;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtlasRequestDTO {
    private String title;
    private String creator;
    @JsonProperty("source_id")
    private Long sourceId;

    @PrePersist
    public Atlas toAtlas() {
        Atlas atlas = new Atlas();

        atlas.setTitle(title);
        atlas.setCreator(creator);
        atlas.setSourceId(sourceId);

        LocalDateTime now = LocalDateTime.now();
        atlas.setCreateAt(now);
        atlas.setUpdatedAt(now);

        return atlas;
    }
}