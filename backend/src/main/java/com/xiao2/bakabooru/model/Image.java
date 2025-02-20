package com.xiao2.bakabooru.model;

import com.xiao2.bakabooru.dto.ImageRequestDTO;
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

    //图片标题
    @Column(name = "title")
    private String title;

    //图片url
    @Column(name = "url")
    private String url;

    //图片序号
    @Column(name = "sn")
    private Long sn;

    //所属图集id
    @Column(name = "atlas_id")
    private Long atlasId;
}
