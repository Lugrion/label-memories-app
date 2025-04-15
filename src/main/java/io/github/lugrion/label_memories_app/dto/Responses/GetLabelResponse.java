package io.github.lugrion.label_memories_app.dto.Responses;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


// PENDING unused class
@Getter
@RequiredArgsConstructor
public class GetLabelResponse {
    private final List<LabelDTO> labels;
}
