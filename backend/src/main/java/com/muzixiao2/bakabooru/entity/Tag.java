package com.muzixiao2.bakabooru.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "type"})
})
public class Tag {

    @Id
    @Column(length = 500)
    private String id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;


    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "tags")
    private Set<Image> images = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (id == null && type != null && name != null) {
            this.id = name + ":" + type;
        }
    }

    public int getReferencedCount() {
        return images != null ? images.size() : 0;
    }
}
