package com.muzixiao2.bakabooru.hub.repository;

import com.muzixiao2.bakabooru.hub.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findAllByOrderByAddedAtAsc();
}