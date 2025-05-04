package com.muzixiao2.bakabooru.source.dto.source;

import lombok.Data;

@Data
public class SourceResponseDTO {
    private String defaultName;
    private String url;
    private String creator;
    private String createdAt;
    private String updatedAt;
}
