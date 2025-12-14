package com.greenlink.backend.features.complaint.dto;

import com.greenlink.backend.features.complaint.model.ComplaintSeverity;
import com.greenlink.backend.features.complaint.model.ComplaintStatus;
import com.greenlink.backend.features.complaint.model.ComplaintType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateComplaintRequest {

    private String title;

    private String description;

    private ComplaintType complaintType;

    private ComplaintSeverity severity;

    private ComplaintStatus status;
}

