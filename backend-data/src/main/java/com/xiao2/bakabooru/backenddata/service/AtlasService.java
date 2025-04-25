package com.xiao2.bakabooru.backenddata.service;

import com.xiao2.bakabooru.backenddata.dto.AtlasQueryDTO;
import com.xiao2.bakabooru.backenddata.dto.AtlasRequestDTO;
import com.xiao2.bakabooru.backenddata.dto.AtlasResponseDTO;
import com.xiao2.bakabooru.backenddata.entity.Atlas;
import com.xiao2.bakabooru.backenddata.entity.Source;
import com.xiao2.bakabooru.backenddata.entity.Tag;
import com.xiao2.bakabooru.backenddata.mapper.AtlasMapper;
import com.xiao2.bakabooru.backenddata.repository.AtlasRepository;
import com.xiao2.bakabooru.backenddata.repository.SourceRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtlasService {
    private final AtlasMapper atlasMapper;
    private final AtlasRepository atlasRepository;
    private final SourceRepository sourceRepository;

    public AtlasService(AtlasMapper atlasMapper, AtlasRepository atlasRepository, SourceRepository sourceRepository

    ) {
        this.atlasMapper = atlasMapper;
        this.atlasRepository = atlasRepository;
        this.sourceRepository = sourceRepository;

    }

    /**
     * 创建图集
     */
    public AtlasResponseDTO createAtlas(AtlasRequestDTO atlasRequestDTO) {
        // 创建实体
        Atlas atlas = atlasMapper.toEntity(atlasRequestDTO);

        // 设置所属图源
        Long sourceId = atlasRequestDTO.getSourceId();
        Source source = sourceRepository.findById(sourceId).orElse(null);
        atlas.setSource(source);

        // 保存实体
        atlasRepository.save(atlas);

        // 返回结果
        return atlasMapper.toResponseDTO(atlas);
    }

    /**
     * 获取图集
     */
    public AtlasResponseDTO getAtlas(Long id) {
        Atlas atlas = atlasRepository.findById(id).orElse(null);
        return atlasMapper.toResponseDTO(atlas);
    }

    /**
     * 查询图集
     */
    @Transactional(readOnly = true)
    public List<AtlasResponseDTO> searchAtlases(AtlasQueryDTO queryDTO) {
        Specification<Atlas> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 标题模糊匹配
            if (queryDTO.getTitle() != null && !queryDTO.getTitle().isEmpty()) {
                predicates.add(cb.like(root.get("title"), "%" + queryDTO.getTitle() + "%"));
            }

            // 创建者模糊匹配
            if (queryDTO.getCreator() != null && !queryDTO.getCreator().isEmpty()) {
                predicates.add(cb.like(root.get("creator"), "%" + queryDTO.getCreator() + "%"));
            }

            if (queryDTO.getTags() != null && !queryDTO.getTags().isEmpty()) {
                Join<Atlas, Tag> tagJoin = root.join("tags", JoinType.INNER);
                predicates.add(tagJoin.get("name").in(queryDTO.getTags()));

                // 👇 让查询结果按 Atlas 分组
                query.groupBy(root.get("id"));

                // 👇 添加 HAVING 子句：标签名称数量必须等于条件数量
                query.having(cb.equal(cb.countDistinct(tagJoin.get("name")), queryDTO.getTags().size()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<Atlas> atlasList = atlasRepository.findAll(specification);
        return atlasList.stream().map(atlasMapper::toResponseDTO).collect(Collectors.toList());
    }

}
