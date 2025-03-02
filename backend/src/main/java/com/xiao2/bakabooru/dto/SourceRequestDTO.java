package com.xiao2.bakabooru.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class SourceRequestDTO {
    private String name;
    private String type;
    private String url;
    @JsonProperty("group_id")
    private Long groupId;
}
