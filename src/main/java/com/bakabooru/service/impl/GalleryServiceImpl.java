package com.bakabooru.service.impl;

import com.bakabooru.dao.GalleryMapper;
import com.bakabooru.pojo.Gallery;
import com.bakabooru.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper imageMapper;

    public Gallery getGallery(int galleryId) {
        return imageMapper.getGallery(galleryId);
    }

}
