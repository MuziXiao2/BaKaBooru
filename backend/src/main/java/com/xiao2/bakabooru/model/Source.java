package com.xiao2.bakabooru.model;

import com.xiao2.bakabooru.dto.SourceRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "source")
@Data
@NoArgsConstructor
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private List<SourceAtlas> sourceAtlases;

    public Source(SourceRequestDTO dto) {
        this.name = dto.getName();
        this.type = dto.getType();
    }
}
