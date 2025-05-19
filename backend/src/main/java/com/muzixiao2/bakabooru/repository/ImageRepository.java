package com.muzixiao2.bakabooru.repository;

import com.muzixiao2.bakabooru.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<Image> findByUuid(String uuid);

    Page<Image> findAll(Specification<Image> spec, Pageable pageable);
}