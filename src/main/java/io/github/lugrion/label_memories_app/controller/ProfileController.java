package io.github.lugrion.label_memories_app.controller;

import io.github.lugrion.label_memories_app.dto.ProfileDTO;
import io.github.lugrion.label_memories_app.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfile() {
        return ResponseEntity.ok(profileService.getProfile());
    }

    @PatchMapping
    public ResponseEntity<ProfileDTO> patchProfile(@RequestBody ProfileDTO profileDTO) {
        return ResponseEntity.ok(profileService.patchProfile(profileDTO));
    }

}
