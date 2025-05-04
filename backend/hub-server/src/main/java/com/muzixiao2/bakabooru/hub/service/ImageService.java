package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.client.SourceClient;
import com.muzixiao2.bakabooru.hub.client.SourceClientRegistry;
import com.muzixiao2.bakabooru.hub.dto.image.ImageReferenceDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageRemoteDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageResponseDTO;
import com.muzixiao2.bakabooru.hub.dto.image.ImageUploadRemoteDTO;
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

    // 添加图片
    @Transactional
    public ImageResponseDTO addImage(Long sourceId, String atlasUuid, ImageReferenceDTO imageReferenceDTO, MultipartFile file) {
        SourceClient sourceClient = sourceClientRegistry.getClient(sourceId);
        ImageUploadRemoteDTO imageUploadRemoteDTO = sourceClient.uploadImage(file).getData();
        imageReferenceDTO.setHash(imageUploadRemoteDTO.getHash());
        ImageRemoteDTO imageRemoteDTO = sourceClient.addImage(atlasUuid, imageReferenceDTO).getData();
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
