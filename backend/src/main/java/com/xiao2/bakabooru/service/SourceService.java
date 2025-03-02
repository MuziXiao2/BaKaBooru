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

    //添加图源
    public Source addSource(SourceRequestDTO sourceRequestDTO) {
        // 创建Source对象
        Source source = SourceConverter.toSource(sourceRequestDTO);
        return sourceRepository.save(source);
    }

    //获取所有图源
    public List<SourceResponseDTO> getAllSource() {
        List<Source> sources = sourceRepository.findAll();
        return sources
                .stream()
                .map(SourceConverter::toSourceResponseDTO)
                .collect(Collectors.toList());
    }

}