package com.xiao2.bakabooru.backenddata.service;

import com.xiao2.bakabooru.backenddata.converter.GroupConverter;
import com.xiao2.bakabooru.backenddata.converter.SourceConverter;
import com.xiao2.bakabooru.backenddata.dto.GroupRequestDTO;
import com.xiao2.bakabooru.backenddata.dto.GroupResponseDTO;
import com.xiao2.bakabooru.backenddata.model.Group;
import com.xiao2.bakabooru.backenddata.repository.GroupRepository;
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
        Group group = GroupConverter.toGroup(groupRequestDTO);

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
                .map(GroupConverter::toGroupResponseDTO)
                .collect(Collectors.toList());
    }

}
