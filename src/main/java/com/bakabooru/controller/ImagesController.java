package com.bakabooru.controller;

import com.bakabooru.pojo.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController("/images")
public class ImagesController {

    @PostMapping
    public ResponseEntity upload( )  {
        for (MultipartFile imageFile : imageFiles) {
            log.info("文件上传, 文件名: {}", imageFile.getOriginalFilename());
            Image image = galleryService.addImage(imageFile, galleryId);
            log.info("文件上传完成,文件访问的url: {}", image.getUrl());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity delete() {

    }


}
