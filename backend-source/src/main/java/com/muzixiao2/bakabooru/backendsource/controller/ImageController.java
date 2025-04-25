package com.muzixiao2.bakabooru.backendsource.controller;

import com.muzixiao2.bakabooru.backendsource.dto.*;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import com.muzixiao2.bakabooru.backendsource.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @GetMapping("/{hash}")
    public ResponseResult<ImageResponseDTO> getImage(@PathVariable String hash) {
        ImageResponseDTO imageResponseDTO = imageService.getImage(hash);
        return ResponseResult.success(imageResponseDTO, "获取图片成功");
    }

//    @GetMapping("/{uuid}/meta")
//    public ResponseResult<ImageResponseDTO> addImage() {
//        ImageResponseDTO imageResponseDTO = imageService.addImage();
//        return ResponseResult.success(imageResponseDTO, "添加图片成功");
//    }
}
