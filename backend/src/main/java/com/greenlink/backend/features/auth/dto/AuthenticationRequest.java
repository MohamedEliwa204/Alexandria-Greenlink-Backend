package com.greenlink.backend.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequest(
        String email,
        String password
) {}