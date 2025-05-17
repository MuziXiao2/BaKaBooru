package com.muzixiao2.bakabooru.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFileRequestDTO {
    private String title;
    private String hash;
}
