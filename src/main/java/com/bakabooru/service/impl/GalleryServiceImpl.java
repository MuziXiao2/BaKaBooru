package com.bakabooru.service.impl;

import com.bakabooru.dao.GalleryMapper;
import com.bakabooru.pojo.Gallery;
import com.bakabooru.pojo.Image;
import com.bakabooru.service.GalleryService;
import com.bakabooru.utils.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper imageMapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    public Gallery getGallery(int galleryId) {
        Gallery gallery = new Gallery();

        gallery.setId(galleryId);
        gallery.setName(imageMapper.getGalleryNameByGalleryId(galleryId));
        gallery.setImages(imageMapper.getImagesByGalleryId(galleryId));

        return gallery;
    }

    @Override
    public Image addImage(MultipartFile imageFile, int galleryId) throws IOException {
        String url = aliOSSUtils.upload(imageFile);

        Image image = new Image();
        image.setId(imageMapper.getNextImageId());
        image.setUrl(url);
        image.setTitle(imageFile.getOriginalFilename());
        imageMapper.addImage(image);
        imageMapper.addGalleryImage(galleryId, image.getId());

        return image;
    }

    @Override
    public List<Image> getImagesByGalleryId(int galleryId) {
        return imageMapper.getImagesByGalleryId(galleryId);
    }
}