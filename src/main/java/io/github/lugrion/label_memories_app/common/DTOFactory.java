package io.github.lugrion.label_memories_app.common;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.github.lugrion.label_memories_app.dto.LabelDTO;
import io.github.lugrion.label_memories_app.dto.MemoryDTO;
import io.github.lugrion.label_memories_app.entity.Label;
import io.github.lugrion.label_memories_app.entity.Memory;

@Component
public class DTOFactory {
    public MemoryDTO getMemoryDTO(Memory memory) {
        return new MemoryDTO(
                memory.getId(),
                memory.getName());
    }

    public Set<MemoryDTO> getMemoryDTOSet(Set<Memory> memories) {
        return memories.stream()
                .map(this::getMemoryDTO)
                .collect(Collectors.toSet());
    }

    public LabelDTO getLabelDTO(Label label) {
        return new LabelDTO(
                label.getId(),
                label.getTitle());
    }

    public Set<LabelDTO> getLabelDTOSet(Set<Label> labels) {
        return labels.stream()
                .map(this::getLabelDTO)
                .collect(Collectors.toSet());
    }

}
