package com.muzixiao2.bakabooru.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFileResponseDTO {
    private String hash;
    private String title;
    private String url;
    private Long size;
    private Integer width;
    private Integer height;
    private String originalFileName;
    private String extension;
}
