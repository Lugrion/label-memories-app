package io.github.lugrion.label_memories_app.Security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.lugrion.label_memories_app.dto.LoginResponse;
import io.github.lugrion.label_memories_app.dto.Requests.LoginRequest;
import io.github.lugrion.label_memories_app.dto.Requests.RegisterRequest;
import io.github.lugrion.label_memories_app.entity.User;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.Exceptions.BadLoginException;
import io.github.lugrion.label_memories_app.Exceptions.EmailNotFoundException;
import io.github.lugrion.label_memories_app.Exceptions.LogicException;
import io.github.lugrion.label_memories_app.Repositories.UserDataRepository;
import io.github.lugrion.label_memories_app.Repositories.UserRepository;
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
    public String registerAccount(RegisterRequest payload) {
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new LogicException("Email already exists");
        }

        User user = new User();
        user.setEmail(payload.getEmail());
        user.setUsername(payload.getUsername());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        userRepository.save(user);

        UserData userData = new UserData();
        userData.setUser(user);
        userDataRepository.save(userData);

        return "Account created!";
    }

    public LoginResponse loginAccount(LoginRequest payload) {
        User user = userRepository.findByEmail(payload.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));

        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new BadLoginException("Bad credentials");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.getEmail(),
                        payload.getPassword()));

        String jwt = jwtService.generateToken(user);

        return new LoginResponse("Bearer: " + jwt, "Login Success!");
    }
}
