package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.PageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.dto.tag.TagDetailResponseDTO;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.entity.ImageImageFile;
import com.muzixiao2.bakabooru.entity.Tag;
import com.muzixiao2.bakabooru.mapper.ImageMapper;
import com.muzixiao2.bakabooru.mapper.TagMapper;
import com.muzixiao2.bakabooru.repository.ImageFileRepository;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import com.muzixiao2.bakabooru.repository.TagRepository;
import com.muzixiao2.bakabooru.util.HashUtil;
import com.muzixiao2.bakabooru.util.MinIOUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final MinIOUtil minIOUtil;
    private final ImageMapper imageMapper;
    private final TagMapper tagMapper;
    private final ImageRepository imageRepository;
    private final ImageFileRepository imageFileRepository;
    private final TagRepository tagRepository;

    // 添加图片
    @Transactional
    public ImageDetailResponseDTO addImage(ImageRequestDTO imageRequestDTO) {
        Image image = imageMapper.toEntity(imageRequestDTO);
        image = imageRepository.save(image);
        image = imageRepository.findByUuid(image.getUuid()).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return imageMapper.toResponseDTO(image);
    }

    // 获取单个图片
    @Transactional(readOnly = true)
    public ImageDetailResponseDTO getImage(String uuid) {
        Image image = imageRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageDetailResponseDTO imageDetailResponseDTO = imageMapper.toResponseDTO(image);
        imageDetailResponseDTO.setFiles(image
                .getImageImageFiles()
                .stream()
                .map(imageMapper::toResponseDTO)
                .toList()
        );
        return imageDetailResponseDTO;
    }

    // 添加图片文件
    @Transactional
    public ImageFileResponseDTO addImageFile(String uuid, MultipartFile file) {
        String hash = HashUtil.calculateHash(file);
        if (!imageFileRepository.existsByHash(hash)) {
            ImageFileUploadResponseDTO imageFileUploadResponseDTO = minIOUtil.upload(hash, file);
            ImageFile imageFile = imageMapper.toEntity(imageFileUploadResponseDTO);
            imageFileRepository.save(imageFile);
        }
        Image image = imageRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageFile imageFile = imageFileRepository.findByHash(hash).orElseThrow(() -> new IllegalArgumentException("图片文件不存在"));
        ImageImageFile imageImageFile = image.addImageFile(imageFile, file.getOriginalFilename());
        return imageMapper.toResponseDTO(imageImageFile);
    }

    // 查询图片
    @Transactional(readOnly = true)
    public PageResponseDTO<ImageQueryResponseDTO> queryImages(String keyword, String tags, Integer page, Integer size, String sortBy, String sortDirection) {
        // 输入验证
        if (page < 1 || size < 1) {
            throw new IllegalArgumentException("Page and size must be positive");
        }

        // 构建 Pageable
        Pageable pageable = buildPageable(page, size, sortBy, sortDirection);

        // 构建查询条件
        Specification<Image> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 注意：Image 实体中无 tags 字段，若不需要 tags 过滤，请移除以下逻辑
            if (StringUtils.hasText(tags)) {
                String[] tagArray = tags.split(",");
                predicates.add(root.get("tags").in(Arrays.asList(tagArray)));
            }
            if (StringUtils.hasText(keyword)) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"));
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
                    List<ImageImageFile> imageImageFiles = image.getImageImageFiles();
                    if (!imageImageFiles.isEmpty()) {
                        imageQueryResponseDTO.setCoverHash(imageImageFiles.getFirst().getImageFile().getHash());
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

    // 添加标签
    @Transactional
    public TagDetailResponseDTO addTag(String uuid, Long tagId) {
        Image image = imageRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("标签不存在"));
        image.addTag(tag);
        return tagMapper.toResponseDTO(tag);
    }
}