package com.example.elearn.service;

import com.example.elearn.model.AuthRequest;
import com.example.elearn.model.AuthResponse;
import com.example.elearn.model.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);

}
