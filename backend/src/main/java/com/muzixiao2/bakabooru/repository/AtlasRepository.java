package com.muzixiao2.bakabooru.repository;

import com.muzixiao2.bakabooru.entity.Atlas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtlasRepository extends JpaRepository<Atlas, String> {
    Optional<Atlas> findByUuid(String uuid);
}