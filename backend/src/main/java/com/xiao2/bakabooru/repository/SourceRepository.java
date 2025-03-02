package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findAllByGroupId(Long groupId);
}