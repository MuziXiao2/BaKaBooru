package com.muzixiao2.bakabooru.source.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageRequestDTO {
    private String hash;
    private String title;
}