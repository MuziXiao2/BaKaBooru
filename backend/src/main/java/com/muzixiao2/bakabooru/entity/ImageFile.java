package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "image_file")
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Image 引用
    @ManyToOne
    @JoinColumn(name = "image_uuid", nullable = false)
    private Image image;

    // File 引用
    @ManyToOne
    @JoinColumn(name = "file_hash", nullable = false)
    private File file;

    // 文件名
    @Column(name = "file_name", nullable = false)
    private String fileName;
}