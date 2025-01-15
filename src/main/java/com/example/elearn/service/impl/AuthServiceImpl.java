package com.example.elearn.service.impl;

import com.example.elearn.entity.User;
import com.example.elearn.mapper.UserMapper;
import com.example.elearn.model.AuthRequest;
import com.example.elearn.model.AuthResponse;
import com.example.elearn.model.RegisterRequest;
import com.example.elearn.repository.UserRepository;
import com.example.elearn.service.AuthService;
import com.example.elearn.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (repository.existsByUsernameAndEmail(request.getUsername(), request.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        User user = mapper.toRegister(request);
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        val user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        val token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
