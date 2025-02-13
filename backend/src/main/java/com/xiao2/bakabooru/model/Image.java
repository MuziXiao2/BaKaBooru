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

    @Column(name = "name")
    private String name;
    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "position")
    private Long position;
    @Column(name = "atlas_id")
    private Long atlasId;


    public Image(ImageRequestDTO dto) {
        this.name = dto.getName();
        this.fileUrl = dto.getFileUrl();
        this.atlasId = dto.getAtlasId();
    }
}
