package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.tag.ImageTagDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.tag.ImageTagResponseDTO;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageTag;
import com.muzixiao2.bakabooru.mapper.ImageTagMapper;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import com.muzixiao2.bakabooru.repository.ImageTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final ImageRepository imageRepository;
    private final ImageTagRepository imageTagRepository;
    private final ImageTagMapper imageTagMapper;


    // 为图片添加标签
    @Transactional
    public ImageTagDetailResponseDTO addTag(String uuid, String tagName, String tagType) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageTag imageTag = image.addTag(tagName, tagType);
        return imageTagMapper.toResponseDTO(imageTag);
    }

    // 获取图片内所有标签
    @Transactional(readOnly = true)
    public ImageTagResponseDTO getTags(String uuid) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        List<ImageTag> imageTags = image.getImageTags();
        return imageTagMapper.toResponseDTO(imageTags);
    }
}
