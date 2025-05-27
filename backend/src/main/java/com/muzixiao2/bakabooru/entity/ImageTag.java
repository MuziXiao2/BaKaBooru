package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "image_tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"imageUuid", "tagName", "tagType"})
})
public class ImageTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageUuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private Image image;

    private String name;

    @Enumerated(EnumType.STRING)
    private TagType type;

    public enum TagType {
        GENERAL, CHARACTER, COPYRIGHT, ARTIST, META
    }
}

