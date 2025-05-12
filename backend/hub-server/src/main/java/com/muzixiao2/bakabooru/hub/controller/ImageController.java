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
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "图片管理", description = "用于管理图片的接口")
public class ImageController {
    private final ImageService imageService;

    @Operation(
            summary = "上传图片",
            description = "上传一张图片文件，返回图片的哈希、大小等信息"
    )
    @PostMapping(path = "/source/{sourceUuid}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImageUploadResponseDTO> uploadImage(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid,
            @Schema(type = "string", format = "binary")
            @RequestParam("file") MultipartFile file
    ) {
        ImageUploadResponseDTO imageUploadResponseDTO = imageService.uploadImage(sourceUuid, file);
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
    @PostMapping("/source/{sourceUuid}/atlas/{atlasUuid}/image")
    public ApiResponse<ImageResponseDTO> addImage(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid,
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("atlasUuid") String atlasUuid,
            @RequestBody ImageReferenceDTO imageReferenceDTO
    ) {
        ImageResponseDTO imageResponseDTO = imageService.addImage(sourceUuid, atlasUuid, imageReferenceDTO);
        return ApiResponse.success(imageResponseDTO);
    }

    @Operation(
            summary = "获取图片",
            description = "获取图片信息"
    )
    @GetMapping("/source/{sourceUuid}/image/{imageHash}")
    public ApiResponse<ImageResponseDTO> getImage(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid,
            @Parameter(description = "图片哈希值", required = true)
            @PathVariable("imageHash") String imageHash
    ) {
        ImageResponseDTO imageResponseDTO = imageService.getImage(sourceUuid, imageHash);
        return ApiResponse.success(imageResponseDTO);
    }

    @Operation(
            summary = "获取所有图片",
            description = "获取该图集的所有图片"
    )
    @GetMapping("/source/{sourceUuid}/atlas/{atlasUuid}/image")
    public ApiResponse<List<ImageResponseDTO>> getAllImages(
            @Parameter(description = "图源ID", required = true)
            @PathVariable("sourceUuid") String sourceUuid,
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("atlasUuid") String atlasUuid
    ) {
        List<ImageResponseDTO> imageResponseDTOList = imageService.getAllImages(sourceUuid, atlasUuid);
        return ApiResponse.success(imageResponseDTOList);
    }
}
