package com.bakabooru.controller;

import com.bakabooru.pojo.Image;
import com.bakabooru.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    @GetMapping("/gallery/{gallery_id}/images")
    public List<Image> getImages(@PathVariable("gallery_id") int galleryId) {
        return galleryService.getGallery(galleryId).getImages();
    }


}
