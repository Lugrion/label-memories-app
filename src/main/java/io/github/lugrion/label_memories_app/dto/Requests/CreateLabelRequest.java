package io.github.lugrion.label_memories_app.dto.Requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateLabelRequest {
    private final String title;
}
