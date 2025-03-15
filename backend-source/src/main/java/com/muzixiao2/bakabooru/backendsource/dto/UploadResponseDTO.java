package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResponseDTO {
    private String title;
    private String uuid;
    private String extension;
    private String url;
    private Long size;
}
