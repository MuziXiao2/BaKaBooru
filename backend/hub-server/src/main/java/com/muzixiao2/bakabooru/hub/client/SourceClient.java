package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.SourceRemoteDTO;
import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface SourceClient {
    @RequestLine("GET /source")
    ApiResponse<SourceRemoteDTO> fetchSource();
}
