package com.bakabooru.service;

import com.bakabooru.pojo.Gallery;
import com.bakabooru.pojo.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface GalleryService {

    Gallery getGallery(int galleryId);
    Image addImage(MultipartFile imageFile, int galleryId) throws IOException;
    List<Image> getImagesByGalleryId(int galleryId);
}