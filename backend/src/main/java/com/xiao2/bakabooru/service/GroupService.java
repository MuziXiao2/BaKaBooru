package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.converter.SourceConverter;
import com.xiao2.bakabooru.dto.GroupRequestDTO;
import com.xiao2.bakabooru.dto.GroupResponseDTO;
import com.xiao2.bakabooru.model.Group;
import com.xiao2.bakabooru.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    //添加组
    public Group createGroup(GroupRequestDTO groupRequestDTO) {
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
