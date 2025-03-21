package com.xiao2.bakabooru.backenddata.repository;

import com.xiao2.bakabooru.backenddata.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByOrderBySnAsc();
}
