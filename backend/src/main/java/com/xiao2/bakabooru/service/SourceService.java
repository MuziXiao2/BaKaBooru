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
    private SourceGroupRepository sourceGroupRepository;


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
    public SourceGroup addSourceGroup(SourceGroupRequestDTO sourceGroupRequestDTO) {
        // 创建SourceGroup对象
        SourceGroup sourceGroup = SourceConverter.toSourceGroup(sourceGroupRequestDTO);

        // 获取sn (!!!高并发下可能出问题!!!)
        Long sn = sourceGroupRepository.count() + 1;
        sourceGroup.setSn(sn);

        // 保存SourceGroup对象
        sourceGroupRepository.save(sourceGroup);
        return sourceGroup;
    }

    //获取所有组
    public List<SourceGroupResponseDTO> getSourceGroups() {
        List<SourceGroup> sourceGroups = sourceGroupRepository.findAllByOrderBySnAsc();
        return sourceGroups
                .stream()
                .map(SourceConverter::toSourceGroupResponseDTO)
                .collect(Collectors.toList());
    }

}