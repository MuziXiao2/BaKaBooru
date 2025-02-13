package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import com.xiao2.bakabooru.model.SourceAtlas;
import com.xiao2.bakabooru.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private AtlasRepository atlasRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SourceAtlasRepository sourceAtlasRepository;
    @Autowired
    private AtlasImageRepository atlasImageRepository;

    /*图源操作*/
    public Source addSource(SourceRequestDTO sourceRequestDTO) {
        Source source = new Source(sourceRequestDTO);
        return sourceRepository.save(source);
    }

    public List<SourceResponseDTO> getAllSource() {
        List<Source> sources = sourceRepository.findAll();
        return sources.stream()
                .map(SourceResponseDTO::new)
                .collect(Collectors.toList());
    }

    /*图集操作*/
    public Atlas addAtlas(Long sourceId, AtlasRequestDTO atlasRequestDTO) {
        // 创建并保存Atlas对象
        Atlas atlas = new Atlas(atlasRequestDTO);
        atlas = atlasRepository.save(atlas);

        // 找到对应的Source对象
        Source source = sourceRepository.findById(sourceId)
                .orElseThrow(() -> new RuntimeException("Source not found"));


        // 获取Atlas的位置 (!!!高并发下可能出问题!!!)
        Long order = sourceAtlasRepository.countBySourceId(sourceId);

        // 创建并保存SourceAtlas对象
        SourceAtlas sourceAtlas = new SourceAtlas(source, atlas, order);
        sourceAtlasRepository.save(sourceAtlas);

        return atlas;
    }

    public List<AtlasResponseDTO> getAllAtlases(Long sourceId) {
        return sourceAtlasRepository.
                findBySourceIdOrderByAtlasOrderAsc(sourceId).stream()
                .map(SourceAtlas::getAtlas)
                .map(AtlasResponseDTO::new)
                .collect(Collectors.toList());
    }

    /*图片操作*/
    public Image addImage(Long atlasId, ImageRequestDTO imageRequestDTO) {
        // 创建并保存Image对象
        Image image = new Image(imageRequestDTO);
        image = imageRepository.save(image);

        // 找到对应的Atlas对象
        Atlas atlas = atlasRepository.findById(atlasId)
                .orElseThrow(() -> new RuntimeException("Atlas not found"));

        // 获取Image的位置 (!!!高并发下可能出问题!!!)
        Long order = atlasImageRepository.countByAtlasId(atlasId);

        // 创建并保存AtlasImage对象
        AtlasImage atlasImage = new AtlasImage(atlas, image, order);
        atlasImageRepository.save(atlasImage);

        return image;
    }

    public List<ImageResponseDTO> getAllImages(Long atlasId) {
        return atlasImageRepository
                .findByAtlasIdOrderByImageOrderAsc(atlasId).stream()
                .map(AtlasImage::getImage)
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

}