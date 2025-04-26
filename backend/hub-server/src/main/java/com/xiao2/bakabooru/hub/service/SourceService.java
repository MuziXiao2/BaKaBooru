package com.xiao2.bakabooru.hub.service;

import com.xiao2.bakabooru.hub.dto.*;
import com.xiao2.bakabooru.hub.entity.Group;
import com.xiao2.bakabooru.hub.entity.Source;
import com.xiao2.bakabooru.hub.mapper.SourceMapper;
import com.xiao2.bakabooru.hub.repository.GroupRepository;
import com.xiao2.bakabooru.hub.repository.SourceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    private final SourceMapper sourceMapper;
    private final GroupRepository groupRepository;
    private final SourceRepository sourceRepository;

    public SourceService(SourceMapper sourceMapper, GroupRepository groupRepository, SourceRepository sourceRepository) {
        this.sourceMapper = sourceMapper;
        this.groupRepository = groupRepository;
        this.sourceRepository = sourceRepository;
    }

    // 创建图源
    public SourceResponseDTO createSource(SourceRequestDTO sourceRequestDTO) {
        // TODO:后期完成
        return null;
    }

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