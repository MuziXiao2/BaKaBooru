package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.dto.ResponseResult;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import org.springframework.web.bind.annotation.GetMapping;

public interface SourceClient {
    @GetMapping("/api/source")
    ResponseResult<SourceSyncDTO> fetchSource();
}
