package com.muzixiao2.bakabooru.repository;

import com.muzixiao2.bakabooru.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageFileRepository extends JpaRepository<ImageFile, String> {
    Optional<ImageFile> findByHash(String hash);

    boolean existsByHash(String hash);
}