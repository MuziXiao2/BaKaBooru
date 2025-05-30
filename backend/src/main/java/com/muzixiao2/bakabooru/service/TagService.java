package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final ImageRepository imageRepository;

    // 为图片添加标签
    @Transactional
    public List<String> addTag(String uuid, String tag) {
        // 检查图片是否存在
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        // 添加标签
        image.addTag(tag);

        return image.getTags();
    }

    // 为图片更新标签
    @Transactional
    public List<String> updateTags(String uuid, List<String> tags) {
        // 检查图片是否存在
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        // 更新标签
        image.updateTags(tags);

        return image.getTags();
    }

    // 获取图片内所有标签
    @Transactional(readOnly = true)
    public List<String> getTags(String uuid) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return image.getTags();
    }
}
