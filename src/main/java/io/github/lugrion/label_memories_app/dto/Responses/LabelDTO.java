package io.github.lugrion.label_memories_app.dto.Responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LabelDTO {
    private final Long id;
    private final String title;
}
