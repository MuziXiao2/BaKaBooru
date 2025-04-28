package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientFactory;
import com.muzixiao2.bakabooru.hub.dto.ResponseResult;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncService {
    private final SourceClientFactory sourceClientFactory;

    public void syncSource(String baseUrl) {
        SourceClient client = sourceClientFactory.createClient(baseUrl);
        ResponseResult<SourceSyncDTO> response = client.fetchSource();

        if (response.isSuccess()) {
            SourceSyncDTO data = response.getData();
        } else {
            // TODO:处理失败逻辑
        }
    }
}
