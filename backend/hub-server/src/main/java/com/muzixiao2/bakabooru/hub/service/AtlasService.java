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
    public AtlasResponseDTO addAtlas(String sourceUuid, AtlasReferenceDTO atlasReferenceDTO) {
        // 获取图源连接
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceUuid);
        // 添加图集
        AtlasRemoteDTO atlasRemoteDTO = sourceClient.addAtlas(atlasReferenceDTO).getData();
        // 返回结果
        return atlasMapper.toResponseDTO(sourceUuid, atlasRemoteDTO);
    }

    //获取单个图集
    @Transactional(readOnly = true)
    public AtlasResponseDTO getAtlas(String sourceUuid, String atlasUuid) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceUuid);
        AtlasRemoteDTO atlasRemoteDTO = sourceClient.getAtlas(atlasUuid).getData();
        return atlasMapper.toResponseDTO(sourceUuid, atlasRemoteDTO);
    }

    //获取所有图集
    @Transactional(readOnly = true)
    public List<AtlasResponseDTO> getAllAtlases(String sourceUuid) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceUuid);
        List<AtlasRemoteDTO> atlasRemoteDTOList = sourceClient.getAllAtlases().getData();
        return atlasRemoteDTOList
                .stream()
                .map(atlasRemoteDTO -> atlasMapper.toResponseDTO(sourceUuid, atlasRemoteDTO))
                .toList();
    }
}
