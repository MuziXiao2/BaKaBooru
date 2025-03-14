package com.xiao2.bakabooru.dto;

import lombok.Data;


@Data
public class AtlasRequestDTO {
    private String title;
    private String creator;
    private Long sourceId;
}