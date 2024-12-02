package com.bakabooru.service;

import com.bakabooru.pojo.ImageVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GalleryService {
    List<ImageVO> getImages(String galleryName);
}