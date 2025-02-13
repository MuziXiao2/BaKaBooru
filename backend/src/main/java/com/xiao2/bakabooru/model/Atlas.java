package com.xiao2.bakabooru.model;

import com.xiao2.bakabooru.dto.AtlasRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "atlas")
@Data
@NoArgsConstructor
public class Atlas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "atlas", cascade = CascadeType.ALL)
    private SourceAtlas sourceAtlas;

    @OneToMany(mappedBy = "atlas", cascade = CascadeType.ALL)
    private List<AtlasImage> atlasImages;

    public Atlas(AtlasRequestDTO dto) {
        this.name = dto.getName();
    }
}
