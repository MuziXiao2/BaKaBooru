package com.muzixiao2.bakabooru.backendsource.repository;

import com.muzixiao2.bakabooru.backendsource.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Long countByAtlasId(Long atlasId);

    List<Image> findByAtlasIdOrderBySnAsc(Long atlasId);
}