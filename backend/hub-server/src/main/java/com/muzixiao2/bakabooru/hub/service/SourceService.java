package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientFactory;
import com.muzixiao2.bakabooru.hub.dto.frontend.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.frontend.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.dto.sync.SourceSyncDTO;
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
    private final SyncService syncService;

    // 添加图源
    public SourceResponseDTO addSource(SourceReferenceDTO sourceReferenceDTO) {
        String groupName = sourceReferenceDTO.getGroupName();

        // 添加组
        if (!groupRepository.existsByName(groupName)) {
            Group group = new Group();
            group.setName(groupName);
            groupRepository.save(group);
        }

        // 创建图源
        Source source = sourceMapper.toEntity(sourceReferenceDTO);

        // 同步图源信息
        source = syncService.syncSource(source);

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