package com.muzixiao2.bakabooru.service;

import com.muzixiao2.bakabooru.dto.PageResponseDTO;
import com.muzixiao2.bakabooru.dto.image.*;
import com.muzixiao2.bakabooru.entity.Image;
import com.muzixiao2.bakabooru.entity.ImageFile;
import com.muzixiao2.bakabooru.entity.ImageImageFile;
import com.muzixiao2.bakabooru.mapper.ImageMapper;
import com.muzixiao2.bakabooru.repository.ImageFileRepository;
import com.muzixiao2.bakabooru.repository.ImageRepository;
import com.muzixiao2.bakabooru.util.HashUtil;
import com.muzixiao2.bakabooru.util.MinIOUtil;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final MinIOUtil minIOUtil;
    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final ImageFileRepository imageFileRepository;

    // 添加图片
    @Transactional
    public ImageResponseDTO addImage(ImageRequestDTO imageRequestDTO) {
        // 创建实体
        Image image = imageMapper.toEntity(imageRequestDTO);
        // 保存实体
        image = imageRepository.save(image);
        // 获取实体
        image = imageRepository.findByUuid(image.getUuid()).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        // 返回响应DTO
        return imageMapper.toResponseDTO(image);
    }

    // 获取单个图片
    @Transactional(readOnly = true)
    public ImageResponseDTO getImage(String uuid) {
        //获取所需实体
        Image image = imageRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        //转换为响应DTO
        ImageResponseDTO imageResponseDTO = imageMapper.toResponseDTO(image);
        imageResponseDTO.setFiles(image
                .getImageImageFiles()
                .stream()
                .map(imageMapper::toResponseDTO)
                .toList()
        );
        return imageResponseDTO;
    }


    // 添加图片文件
    @Transactional
    public ImageFileResponseDTO addImageFile(String uuid, MultipartFile file) {
        //计算图片哈希
        String hash = HashUtil.calculateHash(file);
        //保存图片文件
        if (!imageFileRepository.existsByHash(hash)) {
            ImageFileUploadResponseDTO imageFileUploadResponseDTO = minIOUtil.upload(hash, file);
            ImageFile imageFile = imageMapper.toEntity(imageFileUploadResponseDTO);
            imageFileRepository.save(imageFile);
        }
        //获取所需实体
        Image image = imageRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        ImageFile imageFile = imageFileRepository.findByHash(hash).orElseThrow(() -> new IllegalArgumentException("图片文件不存在"));
        //添加图片文件到图片
        ImageImageFile imageImageFile = image.addImageFile(imageFile, file.getOriginalFilename());
        //转换为响应DTO
        return imageMapper.toResponseDTO(imageImageFile);
    }

    // 获取图片文件临时URL
    @Transactional(readOnly = true)
    public String getImageFileUrl(String hash) {
        ImageFile imageFile = imageFileRepository.findByHash(hash).orElseThrow(() -> new IllegalArgumentException("图片不存在"));
        return minIOUtil.generatePresignedUrl(imageFile.getHash());
    }

    // 查询图片
    @Transactional(readOnly = true)
    public PageResponseDTO<ImageQueryResponseDTO> queryImages(String title, String tags, Integer page, Integer size) {
        // 页码从 1 开始，转换为 0-based 索引
        Pageable pageable = PageRequest.of(page - 1, size);

        // 构建查询条件
        Specification<Image> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(tags)) {
                String[] tagArray = tags.split(",");
                predicates.add(root.get("tags").in(Arrays.asList(tagArray)));
            }
            if (StringUtils.hasText(title)) {
                predicates.add(cb.like(root.get("title"), "%" + title + "%"));
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
                    if (!imageImageFiles.isEmpty())
                        imageQueryResponseDTO
                                .setCoverHash(imageImageFiles
                                        .getFirst()
                                        .getImageFile()
                                        .getHash()
                                );
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
}
