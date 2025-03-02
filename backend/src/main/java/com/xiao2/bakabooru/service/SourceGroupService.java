package com.xiao2.bakabooru.service;

import com.xiao2.bakabooru.converter.SourceGroupConverter;
import com.xiao2.bakabooru.dto.SourceGroupRequestDTO;
import com.xiao2.bakabooru.dto.SourceGroupResponseDTO;
import com.xiao2.bakabooru.model.SourceGroup;

import com.xiao2.bakabooru.repository.SourceGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceGroupService {

    @Autowired
    private SourceGroupRepository sourceGroupRepository;

    //添加组
    public SourceGroup addSourceGroup(SourceGroupRequestDTO sourceGroupRequestDTO) {
        // 创建SourceGroup对象
        SourceGroup sourceGroup = SourceGroupConverter.toSourceGroup(sourceGroupRequestDTO);

        // 获取sn (!!!高并发下可能出问题!!!)
        Long sn = sourceGroupRepository.count() + 1;
        sourceGroup.setSn(sn);

        // 保存SourceGroup对象
        sourceGroupRepository.save(sourceGroup);
        return sourceGroup;
    }

    //获取所有组
    @GetMapping
    public List<SourceGroupResponseDTO> getAllSourceGroup() {
        List<SourceGroup> sourceGroups = sourceGroupRepository.findAll();
        return sourceGroups
                .stream()
                .map(SourceGroupConverter::toSourceGroupResponseDTO)
                .collect(Collectors.toList());
    }
}
