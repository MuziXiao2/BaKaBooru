package com.muzixiao2.bakabooru.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDetailResponseDTO {
    private String hash;
    private String type;
    private String fileName;
    private Integer width;
    private Integer height;
    private Long size;
}
