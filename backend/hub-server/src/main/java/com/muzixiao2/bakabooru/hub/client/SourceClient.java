package com.muzixiao2.bakabooru.hub.client;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageUploadRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceRemoteDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface SourceClient {
    @RequestLine("GET /source")
    ApiResponse<SourceRemoteDTO> getSource();

    @RequestLine("POST /source/atlas")
    @Headers("Content-Type: application/json")
    ApiResponse<AtlasRemoteDTO> addAtlas(AtlasReferenceDTO atlasReferenceDTO);

    @RequestLine("GET /source/atlas/{atlasUuid}")
    ApiResponse<AtlasRemoteDTO> getAtlas(@Param("atlasUuid") String atlasUuid);

    @RequestLine("GET /source/atlas")
    ApiResponse<List<AtlasRemoteDTO>> getAllAtlases();

    @RequestLine("POST /source/image/upload")
    @Headers("Content-Type: multipart/form-data")
    ApiResponse<ImageUploadRemoteDTO> uploadImage(@Param("file") MultipartFile file);

    @RequestLine("POST /source/atlas/{atlasUuid}/image")
    @Headers("Content-Type: application/json")
    ApiResponse<ImageRemoteDTO> addImage(@Param("atlasUuid") String atlasUuid, ImageReferenceDTO imageReferenceDTO);

    @RequestLine("GET /source/image/{imageHash}")
    ApiResponse<ImageRemoteDTO> getImage(@Param("imageHash") String hash);

    @RequestLine("GET /source/atlas/{atlasUuid}/image")
    ApiResponse<List<ImageRemoteDTO>> getImages(@Param("atlasUuid") String atlasUuid);
}