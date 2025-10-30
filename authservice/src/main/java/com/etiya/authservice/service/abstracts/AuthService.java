package com.etiya.authservice.service.abstracts;

import com.etiya.authservice.service.dtos.LoginRequest;
import com.etiya.authservice.service.dtos.RegisterUserRequest;

public interface AuthService {
    void register(RegisterUserRequest request);
    String login(LoginRequest request);
}
