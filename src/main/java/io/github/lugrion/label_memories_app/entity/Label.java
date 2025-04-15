package io.github.lugrion.label_memories_app.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "labels")
@JsonIgnoreProperties({ "userData" })
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "labels_memories", joinColumns = @JoinColumn(name = "label_id"), inverseJoinColumns = @JoinColumn(name = "memory_id"))
    private Set<Memory> memories = new HashSet<>(); // ← OWNER SIDE (controla la tabla)

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;
}
