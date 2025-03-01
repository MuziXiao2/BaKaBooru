package com.xiao2.bakabooru.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UploadResponseDTO {
    private Long size;
    private String url;
}
