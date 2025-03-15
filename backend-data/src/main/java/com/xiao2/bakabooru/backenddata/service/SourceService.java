package com.xiao2.bakabooru.backenddata.service;

import com.xiao2.bakabooru.backenddata.converter.SourceConverter;
import com.xiao2.bakabooru.backenddata.dto.*;
import com.xiao2.bakabooru.backenddata.model.Source;
import com.xiao2.bakabooru.backenddata.repository.SourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SourceService {

    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private RestTemplate restTemplate;

    // 创建图源
    public Source createSource(SourceRequestDTO sourceRequestDTO) {
        return null;//后期完成
    }

    // 添加图源
    public Source addSource(SourceReferenceDTO sourceReferenceDTO) {
        ResponseEntity<ResponseResult<SourceInfo>> responseEntity = restTemplate.exchange(
                sourceReferenceDTO.getUrl() + "?type=source",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseResult<SourceInfo>>() {
                }
        );

        SourceInfo sourceInfo = responseEntity.getBody().getData();

        // 转为 Source 对象
        Source source = SourceConverter.toSource(sourceInfo);

        // 添加属性
        source.setType(sourceReferenceDTO.getType());
        source.setGroupId(sourceReferenceDTO.getGroupId());

        return sourceRepository.save(source);
    }

    // 获取所有图源
    public List<SourceResponseDTO> getSources(Long groupId) {
        List<Source> sources = sourceRepository.findAllByGroupId(groupId);
        return sources
                .stream()
                .map(SourceConverter::toSourceResponseDTO)
                .collect(Collectors.toList());
    }


}