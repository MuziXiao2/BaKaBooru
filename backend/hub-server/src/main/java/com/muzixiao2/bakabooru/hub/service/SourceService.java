package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.dto.frontend.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.frontend.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.Group;
import com.muzixiao2.bakabooru.hub.entity.Source;
import com.muzixiao2.bakabooru.hub.mapper.SourceMapper;
import com.muzixiao2.bakabooru.hub.repository.GroupRepository;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SourceService {
    private final SourceMapper sourceMapper;
    private final GroupRepository groupRepository;
    private final SourceRepository sourceRepository;

    // 添加图源
    public SourceResponseDTO addSource(SourceReferenceDTO sourceReferenceDTO) {
        // 创建实体
        Source source = sourceMapper.toEntity(sourceReferenceDTO);

        // 添加组
        String name = source.getName();
        if (!groupRepository.existsByName(name)) {
            Group group = new Group();
            group.setName(name);
            groupRepository.save(group);
        }

        // 保存实体
        sourceRepository.save(source);

        // 返回结果
        return sourceMapper.toResponseDTO(source);
    }

    // 获取所有图源
    public List<SourceResponseDTO> getAllSource() {
        List<Source> sources = sourceRepository.findAllByOrderByAddedAtAsc();
        return sources
                .stream()
                .map(sourceMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}