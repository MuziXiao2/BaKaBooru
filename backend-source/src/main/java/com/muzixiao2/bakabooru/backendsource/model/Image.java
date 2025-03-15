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
    //图片序号
    @Column(name = "sn")
    private Long sn;
    //所属图集id
    @Column(name = "atlas_id")
    private Long atlasId;
    //图片uuid
    @Column(name = "uuid", unique = true)
    private String uuid;

    //图片标题
    @Column(name = "title")
    private String title;
    //图片类型&后缀名(不含'.')
    @Column(name = "extension")
    private String extension;
    //图片大小(字节)
    @Column(name = "size")
    private Long size;
}
