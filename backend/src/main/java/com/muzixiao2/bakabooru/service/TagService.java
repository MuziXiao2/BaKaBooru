package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.dto.tag.TagRequestDTO;
import com.muzixiao2.bakabooru.entity.Tag;
import com.muzixiao2.bakabooru.mapper.TagMapper;
import com.muzixiao2.bakabooru.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagDetailResponseDTO addTag(TagRequestDTO tagRequestDTO) {
        Tag tag = tagMapper.toEntity(tagRequestDTO);
        tagRepository.save(tag);
        return tagMapper.toResponseDTO(tag);
    }
}
