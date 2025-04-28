package com.muzixiao2.bakabooru.hub.service;

import com.muzixiao2.bakabooru.hub.dto.frontend.GroupResponseDTO;
import com.muzixiao2.bakabooru.hub.entity.Group;
import com.muzixiao2.bakabooru.hub.mapper.GroupMapper;
import com.muzixiao2.bakabooru.hub.repository.GroupRepository;
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
