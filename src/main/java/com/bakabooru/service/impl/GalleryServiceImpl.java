package com.bakabooru.service.impl;

import com.bakabooru.dao.ImageMapper;
import com.bakabooru.pojo.ImageVO;
import com.bakabooru.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private ImageMapper imageMapper;

    public List<ImageVO> getImages(String galleryName) {
        return imageMapper.getImages(galleryName);
    }

}