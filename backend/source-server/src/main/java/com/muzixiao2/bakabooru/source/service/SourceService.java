package com.muzixiao2.bakabooru.source.service;

import com.muzixiao2.bakabooru.source.dto.response.SourceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.muzixiao2.bakabooru.source.config.SourceConfig;

@Service
@RequiredArgsConstructor
public class SourceService {
    private final SourceConfig sourceConfig;

    public SourceResponseDTO getSource() {
        SourceResponseDTO sourceResponseDTO = new SourceResponseDTO();

        sourceResponseDTO.setDefaultName(sourceConfig.getDefaultName());
        sourceResponseDTO.setUrl(sourceConfig.getUrl());
        sourceResponseDTO.setCreator(sourceConfig.getCreator());
        sourceResponseDTO.setCreatedAt(sourceConfig.getCreatedAt());
        sourceResponseDTO.setUpdatedAt(sourceConfig.getUpdatedAt());

        return sourceResponseDTO;
    }
}
