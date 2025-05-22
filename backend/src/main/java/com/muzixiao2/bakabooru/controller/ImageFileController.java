package com.muzixiao2.bakabooru.controller;

import com.muzixiao2.bakabooru.dto.ApiResponse;
import com.muzixiao2.bakabooru.service.ImageFileService;
import com.muzixiao2.bakabooru.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor

@Tag(name = "图片文件管理", description = "用于管理图片文件的接口")
public class ImageFileController {
    private final ImageFileService imageFileService;

    @Operation(
            summary = "获取图片文件临时URL",
            description = "获取图片文件临时URL"
    )
    @GetMapping("/{hash}")
    public ApiResponse<String> getImageFileUrl(
            @Parameter(description = "图片哈希值", required = true)
            @PathVariable("hash") String hash
    ) {
        String url = imageFileService.getImageFileUrl(hash);
        return ApiResponse.success(url);
    }
}
