package com.xiao2.bakabooru.hub.repository;

import com.xiao2.bakabooru.hub.entity.Atlas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AtlasRepository extends JpaRepository<Atlas, Long>, JpaSpecificationExecutor<Atlas> {

}
