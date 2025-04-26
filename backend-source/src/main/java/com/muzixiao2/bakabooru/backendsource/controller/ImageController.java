package com.muzixiao2.bakabooru.backendsource.controller;

import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.entity.Atlas;
import com.muzixiao2.bakabooru.backendsource.entity.AtlasImage;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import com.muzixiao2.bakabooru.backendsource.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    // 上传图片
    @PostMapping
    public ResponseResult<ImageUploadResponseDTO> uploadImage(
            @RequestParam("file") MultipartFile imageFile
    ) {
        if (imageFile == null || imageFile.isEmpty())
            return ResponseResult.error("文件为空", -1);
        ImageUploadResponseDTO imageUploadResponseDTO = imageService.addImage(imageFile);
        return ResponseResult.success(imageUploadResponseDTO, "上传图片成功");
    }
}
