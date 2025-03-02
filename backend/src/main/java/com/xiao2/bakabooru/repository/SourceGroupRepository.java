package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.SourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceGroupRepository extends JpaRepository<SourceGroup, Long> {

    List<SourceGroup> findAllByOrderBySnAsc();
}
