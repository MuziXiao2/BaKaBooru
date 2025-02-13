package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}