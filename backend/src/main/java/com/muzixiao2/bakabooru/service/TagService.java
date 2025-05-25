package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.Tag;
import com.muzixiao2.bakabooru.mapper.TagMapper;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import com.muzixiao2.bakabooru.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final ImageRepository imageRepository;

    //创建新标签
    public TagDetailResponseDTO createTag(TagRequestDTO tagRequestDTO) {
        Tag tag = tagMapper.toEntity(tagRequestDTO);
        tagRepository.save(tag);
        return tagMapper.toResponseDTO(tag);
    }

    // 为图片添加标签
    @Transactional
    public TagDetailResponseDTO addTag(String uuid, String tagId) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("标签不存在"));
        image.addTag(tag);
        return tagMapper.toResponseDTO(tag);
    }

    // 获取图片内所有标签
    @Transactional(readOnly = true)
    public List<TagDetailResponseDTO> getTagDetails(String uuid) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return image
                .getTags()
                .stream()
                .map(tagMapper::toResponseDTO)
                .toList();
    }
}
