package io.github.lugrion.label_memories_app.controller;

import io.github.lugrion.label_memories_app.common.response.GeneralResponse;
import io.github.lugrion.label_memories_app.dto.LabelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.lugrion.label_memories_app.common.request.LabelRequest;
import io.github.lugrion.label_memories_app.service.LabelService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class LabelController {
    private final LabelService labelService;

    @PostMapping("/label")
    public ResponseEntity<LabelDTO> createLabel(@RequestBody LabelRequest payload) {
        return ResponseEntity.ok(labelService.createLabel(payload));
    }

    @GetMapping("/label")
    public ResponseEntity<Set<LabelDTO>> getLabel() {
        return ResponseEntity.ok(labelService.getLabels());
    }

    @PatchMapping("/label/{id}")
    public ResponseEntity<LabelDTO> patchLabel(@PathVariable final Long id, @RequestBody LabelRequest payload) {
        return ResponseEntity.ok(labelService.patchLabel(id, payload));
    }

    @DeleteMapping("/label/{id}")
    public ResponseEntity<GeneralResponse> deleteLabel(@PathVariable final Long id) {
        return ResponseEntity.ok(labelService.deleteLabel(id));
    }
}
