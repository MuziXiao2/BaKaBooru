package com.xiao2.bakabooru.backenddata.dto;

import lombok.Data;

@Data
public class SourceInfo {
    private String name;
    private String url;
    private String creator;
    private String createdAt;
    private String updatedAt;
}
