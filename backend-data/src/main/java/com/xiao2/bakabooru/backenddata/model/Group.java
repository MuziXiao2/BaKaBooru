package com.xiao2.bakabooru.backenddata.model;

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

    //序号
    @Column(name = "sn")
    private Long sn;

    //组名
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
