package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiao2.bakabooru.model.Atlas;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AtlasResponseDTO {
    private Long id;
    private String title;
    @JsonProperty("cover_url")
    private String coverUrl;
    private String creator;
    @JsonProperty("create_at")
    private LocalDateTime createAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("source_id")
    private Long sourceId;


}
