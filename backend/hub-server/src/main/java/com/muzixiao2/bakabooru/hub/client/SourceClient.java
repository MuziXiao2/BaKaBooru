package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceRemoteDTO;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

@Headers("Content-Type: application/json")
public interface SourceClient {
    @RequestLine("GET /source")
    ApiResponse<SourceRemoteDTO> fetchSource();

    @RequestLine("POST /atlas")
    ApiResponse<AtlasRemoteDTO> addAtlas(AtlasReferenceDTO atlasReferenceDTO);
    @RequestLine("GET /atlas")
    ApiResponse<List<AtlasRemoteDTO>> fetchAllAtlases();
}
