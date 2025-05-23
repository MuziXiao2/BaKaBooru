package com.muzixiao2.bakabooru.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDTO {
    private String hash;
    private String type;
    private Long size;
    private Integer width;
    private Integer height;
}
