package com.bakabooru.service;

import com.bakabooru.pojo.UploadImageDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public interface ImageService {
    void addImage(UploadImageDTO uploadImageDTO, String galleryName) throws IOException;

    boolean deleteImage(int id);
}
