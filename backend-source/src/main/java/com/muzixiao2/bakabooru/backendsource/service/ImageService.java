package com.muzixiao2.bakabooru.backendsource.service;

import com.muzixiao2.bakabooru.backendsource.dto.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import com.muzixiao2.bakabooru.backendsource.mapper.ImageMapper;
import com.muzixiao2.bakabooru.backendsource.repository.ImageRepository;
import com.muzixiao2.bakabooru.backendsource.utils.MinIOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final MinIOUtil minIOUtil;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    // 添加图片
    public ImageUploadResponseDTO addImage(MultipartFile imageFile) {
        ImageUploadResponseDTO imageUploadResponseDTO = minIOUtil.upload(imageFile);
        boolean isExists = imageRepository.existsByHash(imageUploadResponseDTO.getHash());
        Image image = imageMapper.toEntity(imageUploadResponseDTO);
        if (!isExists) imageRepository.save(image);
        return imageUploadResponseDTO;
    }
}
