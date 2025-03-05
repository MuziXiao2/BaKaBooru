package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.ImageRequestDTO;
import com.xiao2.bakabooru.dto.ImageResponseDTO;
import com.xiao2.bakabooru.dto.ResponseResult;
import com.xiao2.bakabooru.dto.UploadResponseDTO;
import com.xiao2.bakabooru.model.Image;
import com.xiao2.bakabooru.service.ImageService;
import com.xiao2.bakabooru.utils.MinIOUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /*图片操作*/
    // 上传图片到存储库
    @PostMapping("/upload")
    public UploadResponseDTO uploadImage(@RequestParam("file") MultipartFile file) {
        return MinIOUtil.upload(file);
    }

    // 给图集添加张图片
    @PostMapping
    public ResponseResult<Image> addImage(@RequestBody ImageRequestDTO imageRequestDTO) {
        Image image = imageService.addImage(imageRequestDTO);
        return ResponseResult.success(image, "添加图片成功");
    }

    // 从图集获取所有图片
    @GetMapping("/{atlasId}")
    public List<ImageResponseDTO> getImages(@PathVariable Long atlasId) {
        return imageService.getImages(atlasId);
    }

}
