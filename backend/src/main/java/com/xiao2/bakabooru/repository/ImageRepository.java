package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Long countByAtlasId(Long atlasId);

    List<Image> findByAtlasIdOrderBySnAsc(Long atlasId);
}