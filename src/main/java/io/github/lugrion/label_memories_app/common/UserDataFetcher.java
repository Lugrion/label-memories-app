package io.github.lugrion.label_memories_app.common;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.github.lugrion.label_memories_app.entity.User;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataFetcher {

    private final UserDataRepository userDataRepository;

    public UserData getUserDataByAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return userDataRepository.findByUserId(currentUser.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("CRITICAL ERROR: User Data not found"));
    }
}
