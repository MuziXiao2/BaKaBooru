package com.muzixiao2.bakabooru.repository;

import com.muzixiao2.bakabooru.entity.ImageTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageTagRepository extends JpaRepository<ImageTag, Long> {
    List<ImageTag> findAllByUuid(String uuid);
}
