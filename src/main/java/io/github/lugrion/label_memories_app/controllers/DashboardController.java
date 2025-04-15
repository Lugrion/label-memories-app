package io.github.lugrion.label_memories_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lugrion.label_memories_app.Services.DashboardService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @PostMapping("/filter")
    public ResponseEntity<?> filterMemoriesByLabels(@RequestBody final List<Long> labelIds) {
        return ResponseEntity.ok(dashboardService.filterMemoriesByLabels(labelIds));
    }

}
