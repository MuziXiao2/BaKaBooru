package com.muzixiao2.bakabooru.source.repository;

import com.muzixiao2.bakabooru.source.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {
    Optional<Image> findByHash(String hash);
}