package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.Atlas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtlasRepository extends JpaRepository<Atlas, Long> {
    List<Atlas> findBySourceId(Long sourceId);
}