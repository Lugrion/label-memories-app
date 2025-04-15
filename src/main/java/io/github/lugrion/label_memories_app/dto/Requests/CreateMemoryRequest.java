package io.github.lugrion.label_memories_app.dto.Requests;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemoryRequest {
    private final String name;
    private final Set<Long> labelIds;
}
