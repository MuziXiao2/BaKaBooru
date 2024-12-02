package com.bakabooru.service.impl;

import com.bakabooru.dao.ImageMapper;
import com.bakabooru.pojo.ImageDTO;
import com.bakabooru.pojo.ImageVO;
import com.bakabooru.pojo.UploadImageDTO;
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
    public void addImage(UploadImageDTO uploadImageDTO, String galleryName) throws IOException {
        imageMapper.addImage(new ImageDTO(uploadImageDTO.getName(), aliOSSUtils.upload(uploadImageDTO.getFile()), galleryName));
    }

    @Override
    public boolean deleteImage(int id) {
        return imageMapper.deleteImage(id) != 0;
    }
}