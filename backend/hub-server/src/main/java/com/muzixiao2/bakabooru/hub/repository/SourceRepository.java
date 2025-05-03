package com.muzixiao2.bakabooru.hub.repository;

import com.muzixiao2.bakabooru.hub.entity.SourceMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SourceRepository extends JpaRepository<SourceMeta, Long> {
    Optional<SourceMeta> findById(Long id);

    List<SourceMeta> findAllByOrderByAddedAtAsc();
}