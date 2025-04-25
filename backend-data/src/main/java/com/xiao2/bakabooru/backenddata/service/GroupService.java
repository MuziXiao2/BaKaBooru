package com.xiao2.bakabooru.backenddata.service;

import com.xiao2.bakabooru.backenddata.dto.GroupResponseDTO;
import com.xiao2.bakabooru.backenddata.entity.Group;
import com.xiao2.bakabooru.backenddata.mapper.GroupMapper;
import com.xiao2.bakabooru.backenddata.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    /**
     * 获取所有组
     */
    public List<GroupResponseDTO> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups
                .stream()
                .map(groupMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
