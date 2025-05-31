package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.dto.file.FileDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.file.FileUploadResponseDTO;
import com.muzixiao2.bakabooru.service.FileService;
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
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "文件管理", description = "用于管理图片文件的接口")
public class FileController {
    private final FileService fileService;

    @Operation(
            summary = "获取图片文件临时URL",
            description = "获取图片文件临时URL"
    )
    @GetMapping("/file/{hash}")
    public ApiResponse<String> getImageFileUrl(
            @Parameter(description = "图片哈希值", required = true)
            @PathVariable("hash") String hash
    ) {
        String url = fileService.getFileUrl(hash);
        return ApiResponse.success(url);
    }

    @Operation(
            summary = "为图片添加图片文件",
            description = "为图片添加图片文件"
    )
    @PostMapping(path = "/image/{uuid}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<FileDetailResponseDTO> addImageFile(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid,

            @RequestPart("sourceFile")
            @Schema(type = "string", format = "binary")
            MultipartFile sourceFile
    ) {
        FileDetailResponseDTO fileDetailResponseDTO = fileService.addFile(uuid, sourceFile);
        return ApiResponse.success(fileDetailResponseDTO);
    }

    @Operation(
            summary = "获取图片内所有文件详细信息",
            description = "获取图片内所有文件详细信息"
    )
    @GetMapping("/image/{uuid}/file")
    public ApiResponse<List<FileDetailResponseDTO>> getFileDetails(
            @Parameter(description = "图片UUID", required = true)
            @PathVariable("uuid") String uuid
    ) {
        List<FileDetailResponseDTO> fileDetailResponseDTOList = fileService.getFileDetails(uuid);
        return ApiResponse.success(fileDetailResponseDTOList);
    }
}
