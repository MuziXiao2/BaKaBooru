package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.PageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.dto.tag.ImageTagQueryRequestDTO;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.entity.ImageTag;
import com.muzixiao2.bakabooru.mapper.ImageMapper;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;

    // 添加图片
    @Transactional
    public ImageDetailResponseDTO addImage(ImageRequestDTO imageRequestDTO) {
        Image image = imageMapper.toEntity(imageRequestDTO);
        image = imageRepository.save(image);
        image = imageRepository.findByUuid(image.getUuid()).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return imageMapper.toResponseDTO(image);
    }

    // 获取图片详细信息
    @Transactional(readOnly = true)
    public ImageDetailResponseDTO getImageDetail(String uuid) {
        Image image = imageRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageDetailResponseDTO imageDetailResponseDTO = imageMapper.toResponseDTO(image);
        return imageDetailResponseDTO;
    }

    // 查询图片
    @Transactional(readOnly = true)
    public PageResponseDTO<ImageQueryResponseDTO> queryImages(ImageQueryRequestDTO imageQueryRequestDTO) {
        Integer page = imageQueryRequestDTO.getPage();
        Integer size = imageQueryRequestDTO.getSize();
        String sortBy = imageQueryRequestDTO.getSortBy();
        String sortDirection = imageQueryRequestDTO.getSortDirection();
        ImageTagQueryRequestDTO tags = imageQueryRequestDTO.getTags();
        String keyword = imageQueryRequestDTO.getKeyword();

        // 输入验证
        if (page < 1 || size < 1) {
            throw new IllegalArgumentException("Page and size must be positive");
        }

        // 构建 Pageable
        Pageable pageable = buildPageable(page, size, sortBy, sortDirection);

        // 构建查询条件
        Specification<Image> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 关键词搜索
            if (StringUtils.hasText(keyword)) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"));
            }

            // 标签筛选
            if (tags != null && tags.hasAnyTag()) {
                Subquery<String> subquery = query.subquery(String.class);
                Root<ImageTag> tagRoot = subquery.from(ImageTag.class);
                subquery.select(tagRoot.get("uuid"));

                List<Predicate> tagConditions = new ArrayList<>();

                if (tags.getGeneral() != null && !tags.getGeneral().isEmpty()) {
                    tagConditions.add(cb.and(
                            cb.equal(tagRoot.get("type"), "general"),
                            tagRoot.get("name").in(tags.getGeneral())
                    ));
                }
                if (tags.getCharacter() != null && !tags.getCharacter().isEmpty()) {
                    tagConditions.add(cb.and(
                            cb.equal(tagRoot.get("type"), "character"),
                            tagRoot.get("name").in(tags.getCharacter())
                    ));
                }
                if (tags.getCopyright() != null && !tags.getCopyright().isEmpty()) {
                    tagConditions.add(cb.and(
                            cb.equal(tagRoot.get("type"), "copyright"),
                            tagRoot.get("name").in(tags.getCopyright())
                    ));
                }
                if (tags.getArtist() != null && !tags.getArtist().isEmpty()) {
                    tagConditions.add(cb.and(
                            cb.equal(tagRoot.get("type"), "artist"),
                            tagRoot.get("name").in(tags.getArtist())
                    ));
                }
                if (tags.getMeta() != null && !tags.getMeta().isEmpty()) {
                    tagConditions.add(cb.and(
                            cb.equal(tagRoot.get("type"), "meta"),
                            tagRoot.get("name").in(tags.getMeta())
                    ));
                }

                // 构造 WHERE + HAVING 子查询：必须同时命中所有 tag 条件
                subquery.where(cb.or(tagConditions.toArray(new Predicate[0])));
                subquery.groupBy(tagRoot.get("uuid"));
                subquery.having(cb.equal(cb.countDistinct(tagRoot.get("type")), tagConditions.size()));

                // 主查询 uuid in 子查询结果
                predicates.add(root.get("uuid").in(subquery));

                // 去重
                query.distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };


        // 执行分页查询
        Page<Image> imagePage = imageRepository.findAll(spec, pageable);

        // 转换为 DTO
        List<ImageQueryResponseDTO> content = imagePage
                .getContent()
                .stream()
                .map(image -> {
                    ImageQueryResponseDTO imageQueryResponseDTO = imageMapper.toQueryResponseDTO(image);
                    List<ImageFile> imageFiles = image.getImageFiles();
                    if (!imageFiles.isEmpty()) {
                        imageQueryResponseDTO.setCoverHash(imageFiles.getFirst().getFile().getHash());
                    }
                    return imageQueryResponseDTO;
                }).toList();

        // 构建分页响应
        return new PageResponseDTO<>(
                content,
                imagePage.getTotalElements(),
                imagePage.getTotalPages(),
                imagePage.getNumber() + 1, // 转换为 1-based 页码
                imagePage.getSize()
        );
    }

    private Pageable buildPageable(Integer page, Integer size, String sortBy, String sortDirection) {
        // 默认排序字段和方向
        String sortField = "updatedat";
        Sort.Direction direction = Sort.Direction.DESC;

        // 处理排序字段
        if (StringUtils.hasText(sortBy)) {
            sortField = switch (sortBy) {
                case "title", "createdAt", "updatedAt" -> sortBy;
                default -> throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
            };
        }

        // 处理排序方向
        if (StringUtils.hasText(sortDirection)) {
            try {
                direction = Sort.Direction.fromString(sortDirection);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid sortDirection value: " + sortDirection);
            }
        }

        // 构建 Pageable
        return PageRequest.of(page - 1, size, Sort.by(direction, sortField));
    }
}