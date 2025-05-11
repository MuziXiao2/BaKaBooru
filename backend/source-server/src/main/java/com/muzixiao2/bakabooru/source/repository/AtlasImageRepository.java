package com.muzixiao2.bakabooru.source.repository;

import com.muzixiao2.bakabooru.source.entity.Atlas;
import com.muzixiao2.bakabooru.source.entity.AtlasImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtlasImageRepository extends JpaRepository<AtlasImage, Long> {
    List<AtlasImage> findAllByAtlasUuid(String atlasUuid);

}
