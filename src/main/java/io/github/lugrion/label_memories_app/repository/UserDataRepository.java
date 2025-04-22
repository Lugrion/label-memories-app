package io.github.lugrion.label_memories_app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.lugrion.label_memories_app.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUserId(Long userId);
}
