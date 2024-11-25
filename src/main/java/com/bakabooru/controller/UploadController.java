package com.bakabooru.controller;

import com.bakabooru.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile image) throws IOException {

        log.info("文件上传, 文件名: {}", image.getOriginalFilename());

        String url = aliOSSUtils.upload(image);

        log.info("文件上传完成,文件访问的url: {}", url);

        return  ResponseEntity
                .created(URI.create(url)) // 设置 Location 头
                .body("File uploaded successfully");
    }

}
