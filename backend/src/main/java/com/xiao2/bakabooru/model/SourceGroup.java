package com.xiao2.bakabooru.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "source_group")
@Data
@NoArgsConstructor
public class SourceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //序号
    @Column(name = "sn")
    private Long sn;

    //组名称
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
