package com.bakabooru.service;

import com.bakabooru.pojo.ImageDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface ImageService {
    String uploadImage(MultipartFile imageFile) throws IOException;

    void addImage(ImageDTO imageDTO, String galleryName);

    boolean deleteImage(int id);
}
