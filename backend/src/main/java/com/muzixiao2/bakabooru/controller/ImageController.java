package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.service.ImageService;
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
            summary = "上传图片文件",
            description = "上传一个图片文件，返回图片的哈希、大小等信息"
    )
    @PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImageFileUploadResponseDTO> uploadImageFile(
            @Schema(type = "string", format = "binary")
            @RequestParam("file") MultipartFile file
    ) {
        ImageFileUploadResponseDTO imageFileUploadResponseDTO = imageService.uploadImageFile(file);
        return ApiResponse.success(imageFileUploadResponseDTO);
    }

    @Operation(
            summary = "获取图片文件",
            description = "获取图片信息"
    )
    @GetMapping("/file/{hash}")
    public ApiResponse<ImageFileResponseDTO> getImageFile(
            @Parameter(description = "图片哈希值", required = true)
            @PathVariable("hash") String hash
    ) {
        ImageFileResponseDTO imageFileResponseDTO = imageService.getImageFile(hash);
        return ApiResponse.success(imageFileResponseDTO);
    }

    @Operation(
            summary = "添加图片",
            description = "添加新的图片",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "图片引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ImageRequestDTO.class))
            )
    )
    @PostMapping
    public ApiResponse<ImageResponseDTO> addImage(
            @RequestBody ImageRequestDTO imageRequestDTO
    ) {
        ImageResponseDTO imageResponseDTO = imageService.addImage(imageRequestDTO);
        return ApiResponse.success(imageResponseDTO);
    }

    @Operation(
            summary = "获取图片",
            description = "获取图片"
    )
    @GetMapping("/{uuid}")
    public ApiResponse<ImageResponseDTO> getImage(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        ImageResponseDTO imageResponseDTO = imageService.getImage(uuid);
        return ApiResponse.success(imageResponseDTO);
    }

    @Operation(
            summary = "获取所有图片",
            description = "获取该图源的所有图集"
    )
    @GetMapping
    public ApiResponse<List<ImageResponseDTO>> getAllImages() {
        List<ImageResponseDTO> imageResponseDTOList = imageService.getAllImages();
        return ApiResponse.success(imageResponseDTOList);
    }

    @Operation(
            summary = "为图片添加图片文件",
            description = "为图片添加图片文件",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "图片引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ImageFileRequestDTO.class))
            )
    )
    @PostMapping("/{uuid}")
    public ApiResponse<ImageResponseDTO> addImageFile(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid,
            @RequestBody ImageFileRequestDTO imageFileRequestDTO
    ) {
        ImageResponseDTO imageResponseDTO = imageService.addImageFile(uuid, imageFileRequestDTO);
        return ApiResponse.success(imageResponseDTO);
    }

    @Operation(
            summary = "获取图片内所有图片文件",
            description = "获取图片内所有图片文件"
    )
    @GetMapping("/{uuid}/file")
    public ApiResponse<List<ImageFileResponseDTO>> getAllImageFiles(
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        List<ImageFileResponseDTO> imageFileResponseDTOList = imageService.getAllImageFiles(uuid);
        return ApiResponse.success(imageFileResponseDTOList);
    }
}
