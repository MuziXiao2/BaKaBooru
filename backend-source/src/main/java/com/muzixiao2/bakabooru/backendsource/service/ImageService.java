package com.muzixiao2.bakabooru.backendsource.service;

import com.muzixiao2.bakabooru.backendsource.dto.ImageResponseDTO;
import com.muzixiao2.bakabooru.backendsource.entity.Image;
import com.muzixiao2.bakabooru.backendsource.mapper.ImageMapper;
import com.muzixiao2.bakabooru.backendsource.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    public ImageResponseDTO getImage(String hash) {
        Image image = imageRepository.findByHash(hash).orElse(null);
        if (image == null) throw new IllegalArgumentException("图片不存在");
        return imageMapper.toResponseDTO(image);
    }
}
