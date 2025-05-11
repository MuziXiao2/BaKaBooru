package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientRegistry;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.atlas.AtlasResponseDTO;
import com.muzixiao2.bakabooru.hub.mapper.AtlasMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtlasService {
    private final AtlasMapper atlasMapper;
    private final SourceClientRegistry sourceClientRegistry;

    // 添加图集
    @Transactional
    public AtlasResponseDTO addAtlas(Long sourceId, AtlasReferenceDTO atlasReferenceDTO) {
        // 获取图源连接
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        // 添加图集
        AtlasRemoteDTO atlasRemoteDTO = sourceClient.addAtlas(atlasReferenceDTO).getData();
        // 返回结果
        return atlasMapper.toResponseDTO(atlasRemoteDTO);
    }

    //获取单个图集
    @Transactional(readOnly = true)
    public AtlasResponseDTO getAtlas(Long sourceId, String atlasUuid) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        AtlasRemoteDTO atlasRemoteDTO = sourceClient.getAtlas(atlasUuid).getData();
        return atlasMapper.toResponseDTO(atlasRemoteDTO);
    }

    //获取所有图集
    @Transactional(readOnly = true)
    public List<AtlasResponseDTO> getAllAtlases(Long sourceId) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        List<AtlasRemoteDTO> atlasRemoteDTOList = sourceClient.getAllAtlases().getData();
        return atlasRemoteDTOList.stream().map(atlasMapper::toResponseDTO).toList();
    }
}
