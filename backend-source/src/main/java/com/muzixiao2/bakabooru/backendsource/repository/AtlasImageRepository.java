package com.muzixiao2.bakabooru.backendsource.repository;

import com.muzixiao2.bakabooru.backendsource.entity.Atlas;
import com.muzixiao2.bakabooru.backendsource.entity.AtlasImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtlasImageRepository extends JpaRepository<AtlasImage, Long> {
    List<AtlasImage> findAllByAtlasOrderBySortOrderAsc(Atlas atlas);

}
