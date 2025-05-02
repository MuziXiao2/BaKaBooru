package com.muzixiao2.bakabooru.hub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "`group`")
@Data
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //组名
    @Column(name = "name", unique = true)
    private String name;
}
