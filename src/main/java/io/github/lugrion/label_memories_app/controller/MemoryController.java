package io.github.lugrion.label_memories_app.controller;

import io.github.lugrion.label_memories_app.common.request.FilterRequest;
import io.github.lugrion.label_memories_app.common.response.GeneralResponse;
import io.github.lugrion.label_memories_app.dto.MemoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.lugrion.label_memories_app.common.request.MemoryRequest;
import io.github.lugrion.label_memories_app.service.MemoryService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/memory")
public class MemoryController {
    private final MemoryService memoryService;

    @PostMapping
    public ResponseEntity<MemoryDTO> createMemory(@RequestBody MemoryRequest payload) {
        return ResponseEntity.ok(memoryService.createMemory(payload));
    }

    @GetMapping
    public ResponseEntity<Set<MemoryDTO>> getMemory() {
        return ResponseEntity.ok(memoryService.getMemories());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoryDTO> patchMemory(@PathVariable("id") final Long id, @RequestBody MemoryRequest payload) {
        return ResponseEntity.ok(memoryService.patchMemory(id, payload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteMemory(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(memoryService.deleteMemory(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<Set<MemoryDTO>> filterMemoriesByLabels(@RequestBody final FilterRequest payload) {
        return ResponseEntity.ok(memoryService.filterMemoriesByLabels(payload));
    }
}
