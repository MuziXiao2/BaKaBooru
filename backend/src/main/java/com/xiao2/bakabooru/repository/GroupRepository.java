package com.xiao2.bakabooru.repository;

import com.xiao2.bakabooru.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByOrderBySnAsc();
}
