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

    private String name;
    private String url;

    @OneToOne(mappedBy = "image")
    private AtlasImage atlasImage;

    public Image(ImageRequestDTO dto) {
        this.name = dto.getName();
        this.url = dto.getUrl();
    }
}
