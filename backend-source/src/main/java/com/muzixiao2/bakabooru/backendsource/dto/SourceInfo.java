package com.muzixiao2.bakabooru.backendsource.dto;

import lombok.Data;

@Data
public class SourceInfo {
    private String name;
    private String sourceUrl;
    private String ossUrl;
    private String creator;
    private String createdAt;
    private String updatedAt;
}
