package org.example.checker.service;

import jakarta.transaction.Transactional;
import org.example.checker.dto.adjacent.SignUpRequest;
import org.example.checker.model.User;
import org.example.checker.model.invariants.Role;
import org.example.checker.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(SignUpRequest signUpRequest, String role) {
        if(!userRepository.existsByEmail(signUpRequest.getEmail())) {
            User user = new User();

            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(Role.valueOf(role));
            user.setName(signUpRequest.getName());
            user.setPhone(signUpRequest.getPhone());

            userRepository.save(user);
        }
    }

    public boolean existsByRole(String role) {
        return userRepository.existsByRole(Role.valueOf(role));
    }
}
