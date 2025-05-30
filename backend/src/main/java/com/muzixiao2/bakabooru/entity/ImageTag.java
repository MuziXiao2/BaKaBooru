package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "image_tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid", "name", "type"})
})
public class ImageTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String name;

    private String type;

    public static boolean isValidType(String type) {
        return "general".equals(type) ||
                "character".equals(type) ||
                "copyright".equals(type) ||
                "artist".equals(type) ||
                "meta".equals(type);
    }
}

