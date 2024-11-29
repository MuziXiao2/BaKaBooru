package com.bakabooru.service;

import com.bakabooru.pojo.Gallery;
import org.springframework.stereotype.Service;

@Service
public interface GalleryService {

    Gallery getGallery(int galleryId);

}
