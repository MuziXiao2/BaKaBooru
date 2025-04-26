package com.muzixiao2.bakabooru.source.service;

import com.muzixiao2.bakabooru.source.dto.ImageUploadResponseDTO;
import com.muzixiao2.bakabooru.source.entity.Image;
import com.muzixiao2.bakabooru.source.mapper.ImageMapper;
import com.muzixiao2.bakabooru.source.repository.ImageRepository;
import com.muzixiao2.bakabooru.source.utils.HashUtil;
import com.muzixiao2.bakabooru.source.utils.MinIOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final MinIOUtil minIOUtil;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    // 添加图片
    public ImageUploadResponseDTO addImage(MultipartFile imageFile) {
        String hash = HashUtil.calculateHash(imageFile);
        Optional<Image> imageOptional = imageRepository.findByHash(hash);
        ImageUploadResponseDTO imageUploadResponseDTO;
        if (imageOptional.isEmpty()) {
            imageUploadResponseDTO = minIOUtil.upload(hash, imageFile);
            Image image = imageMapper.toEntity(imageUploadResponseDTO);
            imageRepository.save(image);
        } else {
            imageUploadResponseDTO = imageMapper.toUploadResponseDTO(imageOptional.get());
        }
        return imageUploadResponseDTO;
    }
}
