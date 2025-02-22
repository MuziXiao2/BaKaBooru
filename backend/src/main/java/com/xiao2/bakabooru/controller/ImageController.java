package com.xiao2.bakabooru.controller;

import com.xiao2.bakabooru.dto.ImageRequestDTO;
import com.xiao2.bakabooru.dto.ImageResponseDTO;
import com.xiao2.bakabooru.dto.ResponseResult;
import com.xiao2.bakabooru.model.Image;
import com.xiao2.bakabooru.service.SourceService;
import com.xiao2.bakabooru.util.MinIOFileUploader;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private SourceService sourceService;

    /*图片操作*/
    // 上传图片到存储库
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinIOFileUploader.updataImage(file);
    }

    // 给图集添加张图片
    @PostMapping
    public ResponseResult<Image> addImage(@RequestBody ImageRequestDTO imageRequestDTO) {
        Image image = sourceService.addImage(imageRequestDTO);
        return ResponseResult.success(image, "添加图片成功");
    }

    // 从图集获取所有图片
    @GetMapping("/{atlas_id}")
    public List<ImageResponseDTO> getAllImage(@PathVariable("atlas_id") Long atlasId) {
        return sourceService.getAllImage(atlasId);
    }

}
