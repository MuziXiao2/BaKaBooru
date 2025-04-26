package com.xiao2.bakabooru.hub.dto;

import lombok.Data;

import java.util.List;

@Data
public class AtlasQueryDTO {
    private String title;           // 图集标题，模糊匹配
    private String creator;          // 作者名，精确或模糊匹配
    private List<String> tags;      // 标签列表，图集包含这些标签之一或全部
}
