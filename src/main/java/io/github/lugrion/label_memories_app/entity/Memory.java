package io.github.lugrion.label_memories_app.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memories")
@JsonIgnoreProperties({"userData"})
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = true)
    private String url;

    @ManyToMany(mappedBy = "memories") // ← INVERSE SIDE (no gestiona la tabla) 
    private Set<Label> labels = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;
}