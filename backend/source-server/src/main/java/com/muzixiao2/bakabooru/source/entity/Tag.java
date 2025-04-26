package com.muzixiao2.bakabooru.source.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //名称
    private String name;
    //描述
    private String description;

    @ManyToMany(mappedBy = "tags")
    private Set<Atlas> atlas = new HashSet<>();
}
