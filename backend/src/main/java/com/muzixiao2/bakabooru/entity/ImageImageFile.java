package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "image_imagefile")
public class ImageImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Image 引用
    @ManyToOne
    @JoinColumn(name = "image_uuid", nullable = false)
    private Image image;

    // ImageFile 引用
    @ManyToOne
    @JoinColumn(name = "imagefile_hash", nullable = false)
    private ImageFile imageFile;

    // 文件名
    @Column(name = "file_name", nullable = false)
    private String fileName;
}