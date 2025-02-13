package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.SourceAtlas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SourceAtlasRepository extends JpaRepository<SourceAtlas, Long> {
    Long countBySourceId(Long sourceId);

    List<SourceAtlas> findBySourceIdOrderByAtlasOrderAsc(Long sourceId);

}
