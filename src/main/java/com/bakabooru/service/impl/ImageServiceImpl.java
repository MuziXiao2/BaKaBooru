package com.bakabooru.service.impl;

import com.bakabooru.dao.ImageMapper;
import com.bakabooru.pojo.ImageDTO;
import com.bakabooru.service.ImageService;
import com.bakabooru.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Override
    public String uploadImage(MultipartFile imageFile) throws IOException {
        return aliOSSUtils.upload(imageFile);
    }

    @Override
    public void addImage(ImageDTO imageDTO, String galleryName) {
        imageMapper.addImage(imageDTO.getName(), imageDTO.getUrl(), galleryName);
    }

    @Override
    public boolean deleteImage(int id) {
        return imageMapper.deleteImage(id) != 0;
    }
}