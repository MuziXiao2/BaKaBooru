package com.muzixiao2.bakabooru.backendsource.repository;

import com.muzixiao2.bakabooru.backendsource.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // 按 sn 升序查询图集内所有图片
    List<Image> findAllByAtlasIdOrderBySnAsc(Long atlasId);

    // 查找最大 sn
    @Query("SELECT COALESCE(MAX(i.sn), 0) FROM Image i")
    Double findMaxSn();

    // 查找指定 sn 的记录
    Optional<Image> findBySn(Double orderIndex);

    // 查找前一个记录的 sn
    @Query("SELECT i FROM Image i WHERE i.sn < :orderIndex ORDER BY i.sn DESC")
    List<Image> findPrevious(Double orderIndex, Pageable pageable);

    // 查找后一个记录的 sn
    @Query("SELECT i FROM Image i WHERE i.sn > :orderIndex ORDER BY i.sn  ASC")
    List<Image> findNext(Double orderIndex, Pageable pageable);
}