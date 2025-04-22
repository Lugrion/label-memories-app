package io.github.lugrion.label_memories_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HealthCheck {
    @GetMapping("/health")
    public String healthCheck() {
        return "API is Healthy";
    }
}
