package com.greenlink.backend.features.auth.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {}