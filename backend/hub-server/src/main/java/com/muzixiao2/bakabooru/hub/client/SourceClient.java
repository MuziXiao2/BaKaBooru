package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.dto.ResponseResult;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface SourceClient {
    @RequestLine("GET /source")
    ResponseResult<SourceSyncDTO> fetchSource();
}
