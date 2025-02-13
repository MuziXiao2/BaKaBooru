package com.xiao2.bakabooru.model;

import com.xiao2.bakabooru.dto.SourceRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "source")
@Data
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public Source(SourceRequestDTO dto) {
        this.name = dto.getName();
        this.type = dto.getType();
    }
}
