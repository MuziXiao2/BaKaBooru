package com.xiao2.bakabooru.backenddata.repository;

import com.xiao2.bakabooru.backenddata.model.Group;
import com.xiao2.bakabooru.backenddata.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    // 按 sn 升序查询所有图源
    List<Group> findAllByOrderBySnAsc();

    // 查找最大 sn
    @Query("SELECT COALESCE(MAX(i.sn), 0) FROM Group i")
    Double findMaxSn();

    // 查找指定 sn 的记录
    Optional<Group> findBySn(Double orderIndex);

    // 查找前一个记录的 sn
    @Query("SELECT i FROM Group i WHERE i.sn < :orderIndex ORDER BY i.sn DESC")
    List<Group> findPrevious(Double orderIndex, Pageable pageable);

    // 查找后一个记录的 sn
    @Query("SELECT i FROM Group i WHERE i.sn > :orderIndex ORDER BY i.sn  ASC")
    List<Group> findNext(Double orderIndex, Pageable pageable);
}
