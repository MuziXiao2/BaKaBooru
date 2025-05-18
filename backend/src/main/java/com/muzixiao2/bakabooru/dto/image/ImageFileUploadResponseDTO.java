package com.muzixiao2.bakabooru.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFileUploadResponseDTO {
    private String hash;
    private String type;
    private Long size;
    private Integer width;
    private Integer height;
}
