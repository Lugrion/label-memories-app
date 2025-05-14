package io.github.lugrion.label_memories_app.security;

import io.github.lugrion.label_memories_app.common.exceptions.UnauthorizedException;
import io.github.lugrion.label_memories_app.common.response.GeneralResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.lugrion.label_memories_app.common.response.LoginResponse;
import io.github.lugrion.label_memories_app.common.request.LoginRequest;
import io.github.lugrion.label_memories_app.common.request.RegisterRequest;
import io.github.lugrion.label_memories_app.entity.User;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.common.exceptions.LogicException;
import io.github.lugrion.label_memories_app.repository.UserDataRepository;
import io.github.lugrion.label_memories_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // RegisterResponse?
    public GeneralResponse registerAccount(RegisterRequest payload) {

        if (Boolean.TRUE.equals(userRepository.existsByEmail(payload.email()))) {
            throw new LogicException("Email already exists");
        }

        User user = new User();
        user.setEmail(payload.email());
        user.setUsername(payload.username());
        user.setPassword(passwordEncoder.encode(payload.password()));
        userRepository.save(user);

        UserData userData = new UserData();
        userData.setUser(user);
        userDataRepository.save(userData);

        return new GeneralResponse("Account created!");
    }

    public LoginResponse loginAccount(LoginRequest payload) {
        User user = userRepository.findByEmail(payload.email())
                .orElseThrow(() -> new EntityNotFoundException("Email not found"));

        if (!passwordEncoder.matches(payload.password(), user.getPassword())) {
            throw new UnauthorizedException("Bad credentials");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.email(),
                        payload.password()));

        String jwt = jwtService.generateToken(user);

        return new LoginResponse(
                jwt,
                "Bearer",
                jwtService.getExpirationTime()
        );
    }
}
