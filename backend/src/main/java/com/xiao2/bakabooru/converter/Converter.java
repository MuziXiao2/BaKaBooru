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

    public static Image toImage(ImageRequestDTO imageRequestDTO) {
        Image image = new Image();

        image.setTitle(imageRequestDTO.getTitle());
        image.setUrl(imageRequestDTO.getUrl());
        image.setAtlasId(imageRequestDTO.getAtlasId());

        return image;
    }

    public static SourceResponseDTO toSourceResponseDTO(Source source) {
        return new SourceResponseDTO(source);
    }

    public static AtlasResponseDTO toAtlasResponseDTO(Atlas atlas) {
        return new AtlasResponseDTO(atlas);
    }

    public static ImageResponseDTO toImageResponseDTO(Image image) {
        return new ImageResponseDTO(image);
    }
}