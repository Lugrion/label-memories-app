package io.github.lugrion.label_memories_app.service;

import io.github.lugrion.label_memories_app.dto.ProfileDTO;
import io.github.lugrion.label_memories_app.entity.User;
import io.github.lugrion.label_memories_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    public ProfileDTO getProfile(){
        User currentUser = getCurrentUser();
        return new ProfileDTO(currentUser.getUsername());
    }

    public ProfileDTO patchProfile(ProfileDTO profileDTO){
        User currentUser = getCurrentUser();
        currentUser.setUsername(profileDTO.username());
        userRepository.save(currentUser);
        return new ProfileDTO(currentUser.getUsername());
    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
