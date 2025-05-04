package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageUploadRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceRemoteDTO;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Headers("Content-Type: application/json")
public interface SourceClient {
    @RequestLine("GET /source")
    ApiResponse<SourceRemoteDTO> fetchSource();

    @RequestLine("POST /atlas")
    ApiResponse<AtlasRemoteDTO> addAtlas(AtlasReferenceDTO atlasReferenceDTO);

    @RequestLine("GET /atlas/{uuid}")
    ApiResponse<AtlasRemoteDTO> fetchAtlas(@Param("uuid") String atlasUuid);

    @RequestLine("GET /atlas")
    ApiResponse<List<AtlasRemoteDTO>> fetchAllAtlases();

    @RequestLine("POST /image/upload")
    @Body("file=@{file}")
    ApiResponse<ImageUploadRemoteDTO> uploadImage(@Param("file") MultipartFile file);

    @RequestLine("POST /image/{uuid}")
    ApiResponse<ImageRemoteDTO> addImage(@Param("uuid") String atlasUuid, ImageReferenceDTO imageReferenceDTO);

    @RequestLine("GET /image/{uuid}")
    ApiResponse<List<ImageRemoteDTO>> fetchImages(@Param("uuid") String atlasUuid);
}
