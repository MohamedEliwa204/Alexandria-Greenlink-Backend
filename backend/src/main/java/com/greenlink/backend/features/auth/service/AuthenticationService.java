package com.greenlink.backend.features.auth.service;

import com.greenlink.backend.features.auth.dto.AuthenticationRequest;
import com.greenlink.backend.features.auth.dto.AuthenticationResponse;
import com.greenlink.backend.features.auth.dto.RegisterRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    public @Nullable AuthenticationResponse register(RegisterRequest request) {
        return null;
    }

    public @Nullable AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
