package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Atlas;
import lombok.Data;

@Data
public class AtlasResponseDTO {
    private Long id;
    private String name;

    public AtlasResponseDTO(Atlas source) {
        this.id = source.getId();
        this.name = source.getName();
    }
}
