package com.muzixiao2.bakabooru.backendsource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "image")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Column(length = 64, nullable = false, unique = true, updatable = false)
    private String hash;
    //源文件名
    @Column(name = "original_file_name")
    private String originalFileName;
    //源文件后缀名
    @Column(name = "extension")
    private String extension;
    //字节数
    @Column(name = "size")
    private Long size;
    //引用数
    @Column(name = "reference_count")
    private Long referenceCount = 1L;
    //上传时间
    @Column(name = "uploaded_at")
    @CreationTimestamp
    private Instant uploadedAt;
}
