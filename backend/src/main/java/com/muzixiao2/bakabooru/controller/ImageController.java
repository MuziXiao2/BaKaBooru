package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.PageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.dto.tag.TagResponseDTO;
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
            summary = "为图片添加标签",
            description = "为图片添加标签",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "标签信息",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TagRequestDTO.class))
            )
    )
    @PostMapping(path = "/{uuid}/tag")
    public ApiResponse<TagResponseDTO> addTag(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid,
            @RequestBody Long tagId
    ) {
        TagResponseDTO tagResponseDTO = imageService.addTag(uuid, tagId);
        return ApiResponse.success(tagResponseDTO);
    }

    @Operation(
            summary = "为图片添加图片文件",
            description = "为图片添加图片文件"
    )
    @PostMapping(path = "/{uuid}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImageFileResponseDTO> addImageFile(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid,
            @Schema(type = "string", format = "binary")
            @RequestParam("file") MultipartFile file
    ) {
        ImageFileResponseDTO imageFileResponseDTO = imageService.addImageFile(uuid, file);
        return ApiResponse.success(imageFileResponseDTO);
    }

    @Operation(
            summary = "获取图片",
            description = "获取图片详细信息"
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
            summary = "筛选查询图片",
            description = "根据条件筛选查询图片，支持分页"
    )
    @GetMapping
    public ApiResponse<PageResponseDTO<ImageQueryResponseDTO>> queryImages(
            @Parameter(description = "标题关键字（模糊匹配）", example = "A")
            @RequestParam(value = "keyword", required = false) String keyword,
            @Parameter(description = "标签列表，逗号分隔（例如：tag1,tag2）")
            @RequestParam(value = "tags", required = false) String tags,
            @Parameter(description = "页码，从 1 开始", example = "1")
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @Parameter(description = "每页记录数", example = "10")
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @Parameter(description = "排序字段", example = "title")
            @RequestParam(value = "sortBy", defaultValue = "10") String sortBy,
            @Parameter(description = "顺序方向", example = "ASC")
            @RequestParam(value = "sortDirection", defaultValue = "10") String sortDirection
    ) {
        PageResponseDTO<ImageQueryResponseDTO> pageResponse = imageService.queryImages(keyword, tags, page, size, sortBy, sortDirection);
        return ApiResponse.success(pageResponse);
    }
}
