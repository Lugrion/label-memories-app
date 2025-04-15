package io.github.lugrion.label_memories_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lugrion.label_memories_app.dto.Requests.CreateMemoryRequest;
import io.github.lugrion.label_memories_app.Services.MemoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class MemoryController {

    private final MemoryService memoryService;

    @PostMapping("/memory")
    public ResponseEntity<String> createMemory(@RequestBody CreateMemoryRequest payload) {
        return ResponseEntity.ok(memoryService.createMemory(payload));
    }

    // Change to specific reponse
    @GetMapping("/memory")
    public ResponseEntity<?> getMemory() {
        return ResponseEntity.ok(memoryService.getMemories());
    }

    @DeleteMapping("/memory/{id}")
    public ResponseEntity<String> deleteMemory(@PathVariable final Long id) {
        return ResponseEntity.ok(memoryService.deleteMemory(id));
    }
}
