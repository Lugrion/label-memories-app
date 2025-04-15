package io.github.lugrion.label_memories_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lugrion.label_memories_app.dto.Requests.CreateLabelRequest;
import io.github.lugrion.label_memories_app.Services.LabelService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class LabelController {
    private final LabelService labelService;

    @PostMapping("/label")
    public ResponseEntity<String> createLabel(@RequestBody CreateLabelRequest payload) {
        return ResponseEntity.ok(labelService.createLabel(payload));
    }

    // Change to specific reponse
    @GetMapping("/label")
    public ResponseEntity<?> getLabel() {
        return ResponseEntity.ok(labelService.getLabels());
    }

    @DeleteMapping("/label/{id}")
    public ResponseEntity<String> deleteLabel(@PathVariable final Long id) {
        return ResponseEntity.ok(labelService.deleteLabel(id));
    }
}
