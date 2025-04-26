package com.muzixiao2.bakabooru.source.controller;

import com.muzixiao2.bakabooru.source.dto.*;
import com.muzixiao2.bakabooru.source.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "上传图片", description = "上传一张图片文件，返回图片的哈希、大小等信息")
    public ResponseResult<ImageUploadResponseDTO> uploadImage(
            @RequestParam("file") @Schema(type = "string", format = "binary") MultipartFile file
    ) {
        if (file == null || file.isEmpty())
            return ResponseResult.error("文件为空", -1);
        ImageUploadResponseDTO imageUploadResponseDTO = imageService.addImage(file);
        return ResponseResult.success(imageUploadResponseDTO, "上传图片成功");
    }
}
