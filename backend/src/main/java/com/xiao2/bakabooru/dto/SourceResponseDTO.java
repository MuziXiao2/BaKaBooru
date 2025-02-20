package com.xiao2.bakabooru.dto;

import com.xiao2.bakabooru.model.Source;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class SourceResponseDTO {
    private Long id;
    private String name;
    private String type;
    private String url;
    private Instant createAt;
    private Instant updatedAt;

    public SourceResponseDTO(Source source) {
        this.id = source.getId();
        this.name = source.getName();
        this.type = source.getType();
        this.url = source.getUrl();
        this.createAt = source.getCreateAt();
        this.updatedAt = source.getUpdatedAt();
    }
}
