package com.greenlink.backend.features.complaint.dto;

import com.greenlink.backend.features.complaint.model.ComplaintSeverity;
import com.greenlink.backend.features.complaint.model.ComplaintStatus;
import com.greenlink.backend.features.complaint.model.ComplaintType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponse {

    private Long id;

    private String title;

    private String description;

    private ComplaintType complaintType;

    private ComplaintSeverity severity;

    private ComplaintStatus status;

    private Long ticketId;

    private String ticketTitle;

    private Long userId;

    private String userEmail;

    private String userName;

    private String response;

    private Long respondedById;

    private String respondedByName;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;
}

