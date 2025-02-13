package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.AtlasImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtlasImageRepository extends JpaRepository<AtlasImage, Long> {
    Long countByAtlasId(Long atlasId);

    List<AtlasImage> findByAtlasIdOrderByImageOrderAsc(Long atlasId);
}
