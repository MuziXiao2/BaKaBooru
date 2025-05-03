package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientRegistry;
import com.muzixiao2.bakabooru.hub.dto.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.SourceRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.SourceMeta;
import com.muzixiao2.bakabooru.hub.mapper.SourceMapper;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceService {
    private final SourceMapper sourceMapper;
    private final SourceRepository sourceRepository;
    private final SourceClientRegistry sourceClientRegistry;

    // 添加图源
    public SourceResponseDTO addSource(SourceReferenceDTO sourceReferenceDTO) {
        // 创建图源
        SourceMeta sourceMeta = sourceMapper.toMeta(sourceReferenceDTO);
        // 保存图源
        sourceMeta = sourceRepository.save(sourceMeta);
        // 添加连接
        sourceClientRegistry.addClient(sourceMeta.getUrl());
        // 返回结果
        return getSource(sourceMeta.getId());
    }

    //获取单个图源信息
    public SourceResponseDTO getSource(Long id) {
        SourceMeta sourceMeta = sourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("图源不存在"));
        SourceClient client = sourceClientRegistry.getClient(sourceMeta.getUrl())
                .orElseThrow(() -> new RuntimeException("无法建立连接:" + sourceMeta.getUrl()));
        SourceRemoteDTO sourceRemoteDTO = client.fetchSource().getData();
        return sourceMapper.toSourceResponseDTO(sourceMeta, sourceRemoteDTO);
    }

    // 获取所有图源信息
    public List<SourceResponseDTO> getAllSources() {
        List<SourceMeta> sourceMetas = sourceRepository.findAllByOrderByAddedAtAsc();
        return sourceMetas.stream().map(sourceMeta -> getSource(sourceMeta.getId())).toList();
    }
}