package com.greenlink.backend.features.auth.dto;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password
) {}