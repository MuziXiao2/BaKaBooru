package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUploadResponseDTO {
    private String hash;
    private String originalFileName;
    private String extension;
    private Long size;
}
