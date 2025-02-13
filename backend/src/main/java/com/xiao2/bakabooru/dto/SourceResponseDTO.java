package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Source;
import lombok.Data;

@Data
public class SourceResponseDTO {
    private Long id;
    private String name;
    private String type;

    public SourceResponseDTO(Source source) {
        this.id = source.getId();
        this.name = source.getName();
        this.type = source.getType();
    }
}
