package com.greenlink.backend.features.complaint.controller;

import com.greenlink.backend.features.complaint.dto.ComplaintResponse;
import com.greenlink.backend.features.complaint.dto.CreateComplaintRequest;
import com.greenlink.backend.features.complaint.dto.RespondToComplaintRequest;
import com.greenlink.backend.features.complaint.dto.UpdateComplaintRequest;
import com.greenlink.backend.features.complaint.model.ComplaintStatus;
import com.greenlink.backend.features.complaint.service.ComplaintService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    // Create a new complaint
    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(
            @Valid @RequestBody CreateComplaintRequest request,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        ComplaintResponse response = complaintService.createComplaint(request, userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all complaints for the authenticated user
    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> getUserComplaints(Authentication authentication) {
        String userEmail = authentication.getName();
        List<ComplaintResponse> complaints = complaintService.getUserComplaints(userEmail);
        return ResponseEntity.ok(complaints);
    }

    // Get a specific complaint by ID
    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        ComplaintResponse complaint = complaintService.getComplaintById(id, userEmail);
        return ResponseEntity.ok(complaint);
    }

    // Update a complaint
    @PutMapping("/{id}")
    public ResponseEntity<ComplaintResponse> updateComplaint(
            @PathVariable Long id,
            @Valid @RequestBody UpdateComplaintRequest request,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        ComplaintResponse updatedComplaint = complaintService.updateComplaint(id, request, userEmail);
        return ResponseEntity.ok(updatedComplaint);
    }

    // Delete a complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        complaintService.deleteComplaint(id, userEmail);
        return ResponseEntity.noContent().build();
    }

    // Get complaints by ticket
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<ComplaintResponse>> getComplaintsByTicket(
            @PathVariable Long ticketId,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        List<ComplaintResponse> complaints = complaintService.getComplaintsByTicket(ticketId, userEmail);
        return ResponseEntity.ok(complaints);
    }

    // Search complaints by status
    @GetMapping("/search/status")
    public ResponseEntity<List<ComplaintResponse>> searchByStatus(
            @RequestParam ComplaintStatus status,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        List<ComplaintResponse> complaints = complaintService.searchByStatus(status, userEmail);
        return ResponseEntity.ok(complaints);
    }

    // Admin: Respond to a complaint
    @PostMapping("/{id}/respond")
    public ResponseEntity<ComplaintResponse> respondToComplaint(
            @PathVariable Long id,
            @Valid @RequestBody RespondToComplaintRequest request,
            Authentication authentication
    ) {
        String adminEmail = authentication.getName();
        ComplaintResponse response = complaintService.respondToComplaint(id, request, adminEmail);
        return ResponseEntity.ok(response);
    }
}

