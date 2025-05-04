package com.muzixiao2.bakabooru.hub.dto.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageReferenceDTO {
    private String title;
    private String hash;
}
