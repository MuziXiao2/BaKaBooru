package com.muzixiao2.bakabooru.entity;

import com.muzixiao2.bakabooru.enums.TagType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "type", "variant"})
})
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TagType type;

    @Column(nullable = false)
    private String name;

    @Column
    private String variant;

    @Column(length = 500)
    private String description;

    @ManyToMany(mappedBy = "tags")
    private Set<Image> images = new HashSet<>();

}
