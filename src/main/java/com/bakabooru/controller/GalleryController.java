package com.bakabooru.controller;

import com.bakabooru.pojo.Image;
import com.bakabooru.service.GalleryService;
import com.bakabooru.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/gallery/{gallery_id}/images")
    public List<Image> getImages(@PathVariable("gallery_id") int galleryId) {
        return galleryService.getGallery(galleryId).getImages();
    }

    @PostMapping("/gallery/{gallery_id}/upload")
    @ResponseBody
    public ResponseEntity uploadImage(@PathVariable("gallery_id") int galleryId, @RequestParam("images") List<MultipartFile> imageFiles) throws IOException {
        for (MultipartFile imageFile : imageFiles) {
            log.info("文件上传, 文件名: {}", imageFile.getOriginalFilename());
            Image image = galleryService.addImage(imageFile, galleryId);
            log.info("文件上传完成,文件访问的url: {}", image.getUrl());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
