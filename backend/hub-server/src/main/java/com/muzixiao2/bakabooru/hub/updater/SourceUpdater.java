package com.muzixiao2.bakabooru.hub.updater;

import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import com.muzixiao2.bakabooru.hub.entity.Source;
import org.springframework.stereotype.Component;

@Component
public class SourceUpdater {
    public void update(Source target, SourceSyncDTO dto) {
        target.setUrl(dto.getUrl());
        target.setDefaultName(dto.getDefaultName());
        target.setCreator(dto.getCreator());
        target.setCreatedAt(dto.getCreatedAt());
        target.setUpdatedAt(dto.getUpdatedAt());
    }
}
