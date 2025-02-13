package com.xiao2.bakabooru.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "atlas_image")
@Data
@NoArgsConstructor
public class AtlasImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atlas_id")
    private Atlas atlas;

    @OneToOne
    @JoinColumn(name = "image_id", unique = true)
    private Image image;

    @Column(name = "image_order")
    private Long imageOrder;

    public AtlasImage(Atlas atlas, Image image, Long imageOrder) {
        this.atlas = atlas;
        this.image = image;
        this.imageOrder = imageOrder;
    }
}
