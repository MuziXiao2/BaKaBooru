package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.PageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "图片管理", description = "用于管理图片的接口")
public class ImageController {
    private final ImageService imageService;

    @Operation(
            summary = "添加图片",
            description = "添加新的图片",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "图片引用信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ImageRequestDTO.class))
            )
    )
    @PostMapping("/image")
    public ApiResponse<ImageDetailResponseDTO> addImage(
            @RequestBody ImageRequestDTO imageRequestDTO
    ) {
        ImageDetailResponseDTO imageDetailResponseDTO = imageService.addImage(imageRequestDTO);
        return ApiResponse.success(imageDetailResponseDTO);
    }

    @Operation(
            summary = "获取图片详细信息",
            description = "获取图片详细信息"
    )
    @GetMapping("/image/{uuid}")
    public ApiResponse<ImageDetailResponseDTO> getImageDetail(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        ImageDetailResponseDTO imageDetailResponseDTO = imageService.getImageDetail(uuid);
        return ApiResponse.success(imageDetailResponseDTO);
    }

    @Operation(
            summary = "查询图片",
            description = "根据条件筛选查询图片，支持分页"
    )
    @GetMapping("/image")
    public ApiResponse<PageResponseDTO<ImageQueryResponseDTO>> queryImages(
            @ParameterObject @ModelAttribute ImageQueryRequestDTO imageQueryRequestDTO
    ) {
        PageResponseDTO<ImageQueryResponseDTO> pageResponse = imageService.queryImages(imageQueryRequestDTO);
        return ApiResponse.success(pageResponse);
    }
}
