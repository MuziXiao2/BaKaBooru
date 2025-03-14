package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.converter.*;
import com.xiao2.bakabooru.dto.*;
import com.xiao2.bakabooru.model.*;
import com.xiao2.bakabooru.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private GroupRepository groupRepository;


    //添加图源
    public Source addSource(SourceRequestDTO sourceRequestDTO) {
        // 创建Source对象
        Source source = SourceConverter.toSource(sourceRequestDTO);
        return sourceRepository.save(source);
    }

    //获取所有图源
    public List<SourceResponseDTO> getSources(Long groupId) {
        List<Source> sources = sourceRepository.findAllByGroupId(groupId);
        return sources
                .stream()
                .map(SourceConverter::toSourceResponseDTO)
                .collect(Collectors.toList());
    }

    //添加组
    public Group addGroup(GroupRequestDTO groupRequestDTO) {
        // 创建Group对象
        Group group = SourceConverter.toGroup(groupRequestDTO);

        // 获取sn (!!!高并发下可能出问题!!!)
        Long sn = groupRepository.count() + 1;
        group.setSn(sn);

        // 保存Group对象
        groupRepository.save(group);
        return group;
    }

    //获取所有组
    public List<GroupResponseDTO> getGroups() {
        List<Group> groups = groupRepository.findAllByOrderBySnAsc();
        return groups
                .stream()
                .map(SourceConverter::toGroupResponseDTO)
                .collect(Collectors.toList());
    }

}