package com.greenlink.backend.features.complaint.dto;

import com.greenlink.backend.features.complaint.model.ComplaintSeverity;
import com.greenlink.backend.features.complaint.model.ComplaintType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateComplaintRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Complaint type is required")
    private ComplaintType complaintType;

    @NotNull(message = "Severity is required")
    private ComplaintSeverity severity;

    @NotNull(message = "Ticket ID is required")
    private Long ticketId;
}

