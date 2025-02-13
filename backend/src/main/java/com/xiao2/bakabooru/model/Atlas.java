package com.xiao2.bakabooru.model;

import com.xiao2.bakabooru.dto.AtlasRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "atlas")
@Data
@NoArgsConstructor
public class Atlas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "source_id")
    private Long sourceId;

    public Atlas(AtlasRequestDTO dto) {
        this.name = dto.getName();

        this.sourceId = dto.getSourceId();
    }
}
