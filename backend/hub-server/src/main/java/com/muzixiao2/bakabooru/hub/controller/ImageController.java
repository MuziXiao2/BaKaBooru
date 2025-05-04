package com.muzixiao2.bakabooru.hub.controller;

import com.muzixiao2.bakabooru.hub.dto.ApiResponse;
import com.muzixiao2.bakabooru.hub.dto.image.ImageReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.hub.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Tag(name = "图片管理", description = "用于管理图片的接口")
public class ImageController {
    private final ImageService imageService;

    @Operation(
            summary = "上传图片",
            description = "上传一张图片文件，返回图片的哈希、大小等信息"
    )
    @PostMapping(path = "/{sourceId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImageUploadResponseDTO> uploadImage(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceId") Long sourceId,
            @Schema(type = "string", format = "binary")
            @RequestParam("file") MultipartFile file
    ) {
        ImageUploadResponseDTO imageUploadResponseDTO = imageService.uploadImage(sourceId, file);
        return ApiResponse.success(imageUploadResponseDTO);
    }

    @Operation(
            summary = "添加图片",
            description = "添加新的图片",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "图片引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ImageReferenceDTO.class))
            )
    )
    @PostMapping("/{sourceId}/{atlasUuid}")
    public ApiResponse<ImageResponseDTO> addImage(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceId") Long sourceId,
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("atlasUuid") String atlasUuid,
            @RequestBody ImageReferenceDTO imageReferenceDTO
    ) {
        ImageResponseDTO imageResponseDTO = imageService.addImage(sourceId, atlasUuid, imageReferenceDTO);
        return ApiResponse.success(imageResponseDTO);
    }

    @Operation(
            summary = "获取所有图片",
            description = "获取该图集的所有图片"
    )
    @GetMapping("/{sourceId}/{atlasUuid}")
    public ApiResponse<List<ImageResponseDTO>> getAllImages(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceId") Long sourceId,
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("atlasUuid") String atlasUuid
    ) {
        List<ImageResponseDTO> imageResponseDTOList = imageService.getAllImages(sourceId, atlasUuid);
        return ApiResponse.success(imageResponseDTOList);
    }
}
