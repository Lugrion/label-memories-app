package io.github.lugrion.label_memories_app.common.request;

import java.util.Set;

public record MemoryRequest(String name, Set<Long> labelIds) {
}
