package com.greenlink.backend.features.complaint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespondToComplaintRequest {

    @NotBlank(message = "Response is required")
    private String response;
}

