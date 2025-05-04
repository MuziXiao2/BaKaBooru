package com.muzixiao2.bakabooru.source.controller;

import com.muzixiao2.bakabooru.source.dto.*;
import com.muzixiao2.bakabooru.source.dto.image.ImageRequestDTO;
import com.muzixiao2.bakabooru.source.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.source.dto.image.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.source.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传图片", description = "上传一张图片文件，返回图片的哈希、大小等信息")
    public ApiResponse<ImageUploadResponseDTO> uploadImage(
            @RequestParam("file")
            @Schema(type = "string", format = "binary")
            MultipartFile file
    ) {
        if (file == null || file.isEmpty())
            return ApiResponse.error("666", "文件为空");
        ImageUploadResponseDTO imageUploadResponseDTO = imageService.uploadImage(file);
        return ApiResponse.success(imageUploadResponseDTO);
    }

    @PostMapping("/{uuid}")
    @Operation(summary = "向图集添加图片", description = "给指定UUID的图集添加一张图片记录")
    public ApiResponse<ImageResponseDTO> addImage(
            @Parameter(description = "图集的UUID", required = true)
            @PathVariable("uuid") String atlasUuid,
            @RequestBody ImageRequestDTO imageRequestDTO
    ) {
        ImageResponseDTO imageResponseDTO = imageService.addImage(atlasUuid, imageRequestDTO);
        return ApiResponse.success(imageResponseDTO);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "获取图集下所有图片", description = "根据图集UUID，查询该图集中包含的所有图片")
    public ApiResponse<List<ImageResponseDTO>> getImages(
            @Parameter(description = "图集UUID", required = true)
            @PathVariable("uuid") String atlasUuid
    ) {
        List<ImageResponseDTO> imageResponseDTOList = imageService.getImages(atlasUuid);
        return ApiResponse.success(imageResponseDTOList);
    }
}
