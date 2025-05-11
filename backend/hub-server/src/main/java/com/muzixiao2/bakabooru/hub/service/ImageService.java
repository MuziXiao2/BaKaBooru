package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientRegistry;
import com.muzixiao2.bakabooru.hub.dto.image.*;
import com.muzixiao2.bakabooru.hub.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;
    private final SourceClientRegistry sourceClientRegistry;

    // 上传图片
    @Transactional
    public ImageUploadResponseDTO uploadImage(Long sourceId, MultipartFile file) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        ImageUploadRemoteDTO imageUploadRemoteDTO = sourceClient.uploadImage(file).getData();

        return imageMapper.toResponseDTO(imageUploadRemoteDTO);
    }

    // 添加图片
    @Transactional
    public ImageResponseDTO addImage(Long sourceId, String atlasUuid, ImageReferenceDTO imageReferenceDTO) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        ImageRemoteDTO imageRemoteDTO = sourceClient.addImage(atlasUuid, imageReferenceDTO).getData();
        return imageMapper.toResponseDTO(imageRemoteDTO);
    }

    // 获取图片
    @Transactional(readOnly = true)
    public ImageResponseDTO getImage(Long sourceId, String imageHash) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        ImageRemoteDTO imageRemoteDTO = sourceClient.fetchImage(imageHash).getData();
        return imageMapper.toResponseDTO(imageRemoteDTO);
    }

    // 获取所有图片
    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getAllImages(Long sourceId, String atlasUuid) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        List<ImageRemoteDTO> imageRemoteDTOList = sourceClient.fetchImages(atlasUuid).getData();
        return imageRemoteDTOList.stream().map(imageMapper::toResponseDTO).toList();
    }
}
