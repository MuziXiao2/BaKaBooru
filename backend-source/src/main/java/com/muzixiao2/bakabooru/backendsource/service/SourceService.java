package com.muzixiao2.bakabooru.backendsource.service;

import com.muzixiao2.bakabooru.backendsource.dto.SourceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.muzixiao2.bakabooru.backendsource.config.SourceConfig;

@Service
@RequiredArgsConstructor
public class SourceService {
    private final SourceConfig sourceConfig;

    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = new SourceInfo();

        sourceInfo.setDefaultName(sourceConfig.getDefaultName());
        sourceInfo.setDefaultName(sourceConfig.getUrl());
        sourceInfo.setOssUrl(sourceConfig.getOssUrl());
        sourceInfo.setCreator(sourceConfig.getCreator());
        sourceInfo.setCreatedAt(sourceConfig.getCreatedAt());
        sourceInfo.setUpdatedAt(sourceConfig.getUpdatedAt());

        return sourceInfo;
    }

}
