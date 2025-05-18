package com.muzixiao2.bakabooru.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}
