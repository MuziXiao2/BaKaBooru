package com.muzixiao2.bakabooru.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImageTagQueryRequestDTO {
    @Schema(description = "画师标签")
    private List<String> artist;

    @Schema(description = "角色标签")
    private List<String> character;

    @Schema(description = "版权标签")
    private List<String> copyright;

    @Schema(description = "元信息标签")
    private List<String> meta;

    @Schema(description = "通用标签")
    private List<String> general;

    public boolean hasAnyTag() {
        return (general != null && !general.isEmpty()) ||
                (character != null && !character.isEmpty()) ||
                (copyright != null && !copyright.isEmpty()) ||
                (artist != null && !artist.isEmpty()) ||
                (meta != null && !meta.isEmpty());
    }
}
