package com.muzixiao2.bakabooru.dto.tag;

import lombok.Data;

import java.util.List;

@Data
public class ImageTagResponseDTO {
    private List<String> general;
    private List<String> character;
    private List<String> copyright;
    private List<String> artist;
    private List<String> meta;
}
