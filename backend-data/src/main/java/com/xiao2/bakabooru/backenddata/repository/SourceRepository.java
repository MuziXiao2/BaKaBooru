package com.xiao2.bakabooru.backenddata.repository;

import com.xiao2.bakabooru.backenddata.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface SourceRepository extends JpaRepository<Source, Long> {
    List<Source> findAllByOrderByAddedAtAsc();
}