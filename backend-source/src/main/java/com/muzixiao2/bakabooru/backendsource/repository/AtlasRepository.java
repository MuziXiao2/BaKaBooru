package com.muzixiao2.bakabooru.backendsource.repository;

import com.muzixiao2.bakabooru.backendsource.entity.Atlas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface AtlasRepository extends JpaRepository<Atlas, Long> {
    Optional<Atlas> findByUuid(String uuid);

    List<Atlas> findAllByUpdatedAtAfterOrderByCreatedAtAsc(Instant updatedAfter);
}