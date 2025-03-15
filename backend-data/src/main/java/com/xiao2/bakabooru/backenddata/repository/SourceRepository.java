package com.xiao2.bakabooru.backenddata.repository;

import com.xiao2.bakabooru.backenddata.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findAllByGroupId(Long groupId);
}