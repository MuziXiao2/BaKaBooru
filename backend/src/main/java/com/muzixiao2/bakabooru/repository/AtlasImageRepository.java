package com.muzixiao2.bakabooru.repository;

import com.muzixiao2.bakabooru.entity.AtlasImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtlasImageRepository extends JpaRepository<AtlasImage, Long> {
    List<AtlasImage> findAllByAtlasUuid(String atlasUuid);

}
