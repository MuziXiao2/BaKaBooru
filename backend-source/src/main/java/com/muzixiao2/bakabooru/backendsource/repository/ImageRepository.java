package com.muzixiao2.bakabooru.backendsource.repository;

import com.muzixiao2.bakabooru.backendsource.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByHash(String hash);
}