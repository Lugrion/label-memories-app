package io.github.lugrion.label_memories_app.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lugrion.label_memories_app.entity.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {
    Set<Label> findAllByUserDataIdAndIdIn(Long userDataId, Set<Long> labelIds);
    Optional<Label> findByUserDataIdAndId(Long userDataId, Long labelId);
}
