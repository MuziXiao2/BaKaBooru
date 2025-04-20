package com.muzixiao2.bakabooru.backendsource.repository;

import com.muzixiao2.bakabooru.backendsource.model.Atlas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface AtlasRepository extends JpaRepository<Atlas, Long> {
    // 按 sn 升序查询所有图集
    List<Atlas> findAllByOrderBySnAsc();

    // 查找最大 sn
    @Query("SELECT COALESCE(MAX(i.sn), 0) FROM Atlas i")
    Double findMaxSn();

    // 查找指定 sn 的记录
    Optional<Atlas> findBySn(Double orderIndex);

    // 查找前一个记录的 sn
    @Query("SELECT i FROM Atlas i WHERE i.sn < :orderIndex ORDER BY i.sn DESC")
    List<Atlas> findPrevious(Double orderIndex, Pageable pageable);

    // 查找后一个记录的 sn
    @Query("SELECT i FROM Atlas i WHERE i.sn > :orderIndex ORDER BY i.sn  ASC")
    List<Atlas> findNext(Double orderIndex, Pageable pageable);
}