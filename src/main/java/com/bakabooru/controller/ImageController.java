package com.bakabooru.controller;

import com.bakabooru.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/image")
@Tag(name = "ImageController", description = "图片管理")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Operation(summary = "上传图片文件", description = "上传图片文件并返回URL")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImage(@RequestPart("image_file") MultipartFile imageFile) throws IOException {
        String url = imageService.uploadImage(imageFile);
        log.info("文件\"{}\"上传成功, url:{}", imageFile.getOriginalFilename(), url);
        return url;
    }
}
