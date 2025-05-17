package com.muzixiao2.bakabooru.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadResponseDTO {
    private String hash;
    private String originalFileName;
    private String extension;
    private Long size;
    private Integer width;
    private Integer height;
}
