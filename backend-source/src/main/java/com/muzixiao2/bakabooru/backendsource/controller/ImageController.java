package com.muzixiao2.bakabooru.backendsource.controller;

import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    @Operation(summary = "上传图片", description = "上传一张图片文件，返回图片的哈希、大小等信息")
    public ResponseResult<ImageUploadResponseDTO> uploadImage(
            @Parameter(description = "要上传的图片文件", required = true)
            @RequestParam("file") MultipartFile imageFile
    ) {
        if (imageFile == null || imageFile.isEmpty())
            return ResponseResult.error("文件为空", -1);
        ImageUploadResponseDTO imageUploadResponseDTO = imageService.addImage(imageFile);
        return ResponseResult.success(imageUploadResponseDTO, "上传图片成功");
    }
}
