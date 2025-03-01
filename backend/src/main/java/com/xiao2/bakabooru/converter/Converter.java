package com.xiao2.bakabooru.converter;

import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import jakarta.persistence.PrePersist;

import java.time.Instant;
import java.time.LocalDateTime;

public class Converter {
    @PrePersist
    public static Source toSource(SourceRequestDTO sourceRequestDTO) {
        Source source = new Source();

        source.setName(sourceRequestDTO.getName());
        source.setType(sourceRequestDTO.getType());
        source.setUrl(sourceRequestDTO.getUrl());

        Instant now = Instant.now();
        source.setCreateAt(now);
        source.setUpdatedAt(now);

        return source;
    }

    public static SourceResponseDTO toSourceResponseDTO(Source source) {
        SourceResponseDTO sourceResponseDTO = new SourceResponseDTO();

        sourceResponseDTO.setId(source.getId());
        sourceResponseDTO.setName(source.getName());
        sourceResponseDTO.setType(source.getType());
        sourceResponseDTO.setUrl(source.getUrl());
        sourceResponseDTO.setCreateAt(source.getCreateAt());
        sourceResponseDTO.setUpdatedAt(source.getUpdatedAt());

        return sourceResponseDTO;
    }

    @PrePersist
    public static Atlas toAtlas(AtlasRequestDTO atlasRequestDTO) {

        Atlas atlas = new Atlas();

        atlas.setTitle(atlasRequestDTO.getTitle());
        atlas.setCreator(atlasRequestDTO.getCreator());
        atlas.setSourceId(atlasRequestDTO.getSourceId());

        LocalDateTime now = LocalDateTime.now();
        atlas.setCreateAt(now);
        atlas.setUpdatedAt(now);

        return atlas;
    }

    public static AtlasResponseDTO toAtlasResponseDTO(Atlas atlas) {
        AtlasResponseDTO atlasResponseDTO = new AtlasResponseDTO();

        atlasResponseDTO.setId(atlas.getId());
        atlasResponseDTO.setTitle(atlas.getTitle());
        atlasResponseDTO.setCoverUrl(atlas.getCoverUrl());
        atlasResponseDTO.setCreator(atlas.getCreator());
        atlasResponseDTO.setCreateAt(atlas.getCreateAt());
        atlasResponseDTO.setUpdatedAt(atlas.getUpdatedAt());
        atlasResponseDTO.setSourceId(atlas.getSourceId());

        return atlasResponseDTO;
    }

    public static Image toImage(ImageRequestDTO imageRequestDTO) {
        Image image = new Image();

        image.setTitle(imageRequestDTO.getTitle());
        image.setUrl(imageRequestDTO.getUrl());
        image.setSize(imageRequestDTO.getSize());
        image.setAtlasId(imageRequestDTO.getAtlasId());

        return image;
    }

    public static ImageResponseDTO toImageResponseDTO(Image image) {
        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();

        imageResponseDTO.setTitle(image.getTitle());
        imageResponseDTO.setUrl(image.getUrl());
        imageResponseDTO.setSn(image.getSn());
        imageResponseDTO.setAtlasId(image.getAtlasId());
        imageResponseDTO.setSize(image.getSize());

        return imageResponseDTO;
    }
}