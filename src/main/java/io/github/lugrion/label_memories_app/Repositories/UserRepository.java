package io.github.lugrion.label_memories_app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lugrion.label_memories_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
