package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientRegistry;
import com.muzixiao2.bakabooru.hub.dto.source.SourceReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.source.SourceResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.SourceMeta;
import com.muzixiao2.bakabooru.hub.mapper.SourceMapper;
import com.muzixiao2.bakabooru.hub.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SourceService {
    private final SourceMapper sourceMapper;
    private final SourceRepository sourceRepository;
    private final SourceClientRegistry sourceClientRegistry;

    // 添加图源
    @Transactional
    public SourceResponseDTO addSource(SourceReferenceDTO sourceReferenceDTO) {
        // 创建图源
        SourceMeta sourceMeta = sourceMapper.toMeta(sourceReferenceDTO);
        // 保存图源
        sourceMeta = sourceRepository.save(sourceMeta);
        // 添加连接
        sourceClientRegistry.addClient(sourceMeta);
        // 返回结果
        return getSource(sourceMeta.getUuid());
    }

    //获取单个图源信息
    @Transactional(readOnly = true)
    public SourceResponseDTO getSource(String getUuid) {
        SourceMeta sourceMeta = sourceRepository.findByUuid(getUuid)
                .orElseThrow(() -> new IllegalArgumentException("图源不存在"));
        // 获取图源客户端
        SourceClient client = sourceClientRegistry.getClient(getUuid);
        SourceRemoteDTO sourceRemoteDTO = client.getSource().getData();
        return sourceMapper.toSourceResponseDTO(sourceMeta, sourceRemoteDTO);
    }

    // 获取所有图源信息
    @Transactional(readOnly = true)
    public List<SourceResponseDTO> getAllSources() {
        List<SourceMeta> sourceMetas = sourceRepository.findAllByOrderByAddedAtAsc();
        return sourceMetas.stream().map(sourceMeta -> getSource(sourceMeta.getUuid())).toList();
    }
}