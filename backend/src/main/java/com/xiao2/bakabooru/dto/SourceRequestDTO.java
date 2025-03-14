package com.xiao2.bakabooru.dto;

import lombok.Data;


@Data
public class SourceRequestDTO {
    private Long groupId;

    private String name;
    private String type;
    private String url;
}
