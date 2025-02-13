package com.xiao2.bakabooru.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "source_atlas")
@Data
@NoArgsConstructor
public class SourceAtlas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;

    @OneToOne
    @JoinColumn(name = "atlas_id", unique = true)
    private Atlas atlas;

    @Column(name = "atlas_order")
    private Long atlasOrder;

    public SourceAtlas(Source source, Atlas atlas, Long atlasOrder) {
        this.source = source;
        this.atlas = atlas;
        this.atlasOrder = atlasOrder;
    }
}
