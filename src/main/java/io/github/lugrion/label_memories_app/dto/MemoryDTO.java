package io.github.lugrion.label_memories_app.dto;

import java.util.Set;

// Check for null values and throw errors PENDING
public record MemoryDTO(Long id, String name, Set<LabelDTO> labels) {
}
