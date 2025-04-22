package io.github.lugrion.label_memories_app.controller;

import io.github.lugrion.label_memories_app.common.request.FilterRequest;
import io.github.lugrion.label_memories_app.dto.MemoryDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lugrion.label_memories_app.service.DashboardService;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @PostMapping("/filter")
    public ResponseEntity<Set<MemoryDTO>> filterMemoriesByLabels(@RequestBody final FilterRequest payload) {
        return ResponseEntity.ok(dashboardService.filterMemoriesByLabels(payload));
    }
}
