package com.muzixiao2.bakabooru.hub.repository;

import com.muzixiao2.bakabooru.hub.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SourceRepository extends JpaRepository<Source, Long> {
    Optional<Source> findById(Long id);

    List<Source> findAllByOrderByAddedAtAsc();
}