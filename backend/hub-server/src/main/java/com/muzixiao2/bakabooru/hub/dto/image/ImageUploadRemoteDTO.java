package com.muzixiao2.bakabooru.hub.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadRemoteDTO {
    private String hash;
    private String originalFileName;
    private String extension;
    private Long size;
}
