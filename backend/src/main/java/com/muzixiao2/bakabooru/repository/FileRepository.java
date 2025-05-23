package com.muzixiao2.bakabooru.repository;

import com.muzixiao2.bakabooru.entity.File;
import com.muzixiao2.bakabooru.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, String> {
    Optional<File> findByHash(String hash);

    boolean existsByHash(String hash);
}