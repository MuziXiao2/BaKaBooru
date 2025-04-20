package com.muzixiao2.bakabooru.backendsource.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //序号
    @Column(name = "sn")
    private Double sn;
    //所属图集ID
    @Column(name = "atlas_id")
    private Long atlasId;
    //UUID
    @Column(name = "uuid", unique = true)
    private String uuid;
    //标题
    @Column(name = "title")
    private String title;
    //文件后缀名
    @Column(name = "extension")
    private String extension;
    //图片大小
    @Column(name = "size")
    private Long size;
}
