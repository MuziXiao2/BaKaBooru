package com.xiao2.bakabooru.backenddata.service;

import com.xiao2.bakabooru.backenddata.converter.SourceConverter;
import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.model.Source;
import com.xiao2.bakabooru.backenddata.repository.SourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;


    // 创建图源
    public Source createSource(SourceRequestDTO sourceRequestDTO) {
        return null;//后期完成
    }

    // 添加图源
    public Source addSource(SourceReferenceDTO sourceReferenceDTO) {
        // 创建对象
        Source source = SourceConverter.toSource(sourceReferenceDTO);

        // 获取顺序
        Double maxSn = sourceRepository.findMaxSn();
        source.setSn(maxSn + 1.0);

        // 保存对象
        sourceRepository.save(source);
        return source;
    }

    // 获取所有图源
    public List<SourceResponseDTO> getSources() {
        List<Source> sources = sourceRepository.findAllByOrderBySnAsc();
        return sources
                .stream()
                .map(SourceConverter::toSourceResponseDTO)
                .collect(Collectors.toList());
    }


}