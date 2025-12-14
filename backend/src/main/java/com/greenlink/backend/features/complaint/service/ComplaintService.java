package com.greenlink.backend.features.complaint.service;

import com.greenlink.backend.features.auth.model.User;
import com.greenlink.backend.features.auth.repository.UserRepository;
import com.greenlink.backend.features.complaint.dto.ComplaintResponse;
import com.greenlink.backend.features.complaint.dto.CreateComplaintRequest;
import com.greenlink.backend.features.complaint.dto.RespondToComplaintRequest;
import com.greenlink.backend.features.complaint.dto.UpdateComplaintRequest;
import com.greenlink.backend.features.complaint.model.Complaint;
import com.greenlink.backend.features.complaint.model.ComplaintStatus;
import com.greenlink.backend.features.complaint.repository.ComplaintRepository;
import com.greenlink.backend.features.tickets.model.Ticket;
import com.greenlink.backend.features.tickets.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    // Create a new complaint
    public ComplaintResponse createComplaint(CreateComplaintRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Verify the ticket belongs to the user
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Ticket does not belong to user");
        }

        Complaint complaint = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .complaintType(request.getComplaintType())
                .severity(request.getSeverity())
                .status(ComplaintStatus.SUBMITTED)
                .ticket(ticket)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        Complaint savedComplaint = complaintRepository.save(complaint);
        return mapToResponse(savedComplaint);
    }

    // Get all complaints for the authenticated user
    public List<ComplaintResponse> getUserComplaints(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Complaint> complaints = complaintRepository.findByUserId(user.getId());
        return complaints.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get a specific complaint by ID
    public ComplaintResponse getComplaintById(Long complaintId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        // Check if the complaint belongs to the user
        if (!complaint.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Complaint does not belong to user");
        }

        return mapToResponse(complaint);
    }

    // Update a complaint
    public ComplaintResponse updateComplaint(Long complaintId, UpdateComplaintRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        // Check if the complaint belongs to the user
        if (!complaint.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Complaint does not belong to user");
        }

        // Update fields only if they are provided
        if (request.getTitle() != null) {
            complaint.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            complaint.setDescription(request.getDescription());
        }
        if (request.getComplaintType() != null) {
            complaint.setComplaintType(request.getComplaintType());
        }
        if (request.getSeverity() != null) {
            complaint.setSeverity(request.getSeverity());
        }
        if (request.getStatus() != null) {
            complaint.setStatus(request.getStatus());
        }

        Complaint updatedComplaint = complaintRepository.save(complaint);
        return mapToResponse(updatedComplaint);
    }

    // Delete a complaint
    public void deleteComplaint(Long complaintId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        // Check if the complaint belongs to the user
        if (!complaint.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Complaint does not belong to user");
        }

        complaintRepository.delete(complaint);
    }

    // Get complaints by ticket
    public List<ComplaintResponse> getComplaintsByTicket(Long ticketId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Verify the ticket belongs to the user
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Ticket does not belong to user");
        }

        List<Complaint> complaints = complaintRepository.findByTicketIdAndUserId(ticketId, user.getId());
        return complaints.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Search complaints by status
    public List<ComplaintResponse> searchByStatus(ComplaintStatus status, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Complaint> complaints = complaintRepository.findByUserIdAndStatus(user.getId(), status);
        return complaints.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Admin: Respond to a complaint
    public ComplaintResponse respondToComplaint(Long complaintId, RespondToComplaintRequest request, String adminEmail) {
        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        // Check if the user has ADMIN role
        if (admin.getRole() != com.greenlink.backend.features.auth.model.Role.ADMIN) {
            throw new RuntimeException("Unauthorized: Only admins can respond to complaints");
        }

        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setResponse(request.getResponse());
        complaint.setRespondedBy(admin);
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolvedAt(LocalDateTime.now());

        Complaint updatedComplaint = complaintRepository.save(complaint);
        return mapToResponse(updatedComplaint);
    }

    // Helper method to map Complaint entity to ComplaintResponse DTO
    private ComplaintResponse mapToResponse(Complaint complaint) {
        ComplaintResponse.ComplaintResponseBuilder builder = ComplaintResponse.builder()
                .id(complaint.getId())
                .title(complaint.getTitle())
                .description(complaint.getDescription())
                .complaintType(complaint.getComplaintType())
                .severity(complaint.getSeverity())
                .status(complaint.getStatus())
                .ticketId(complaint.getTicket().getId())
                .ticketTitle(complaint.getTicket().getTitle())
                .userId(complaint.getUser().getId())
                .userEmail(complaint.getUser().getEmail())
                .userName(complaint.getUser().getFirstname() + " " + complaint.getUser().getLastname())
                .response(complaint.getResponse())
                .createdAt(complaint.getCreatedAt())
                .resolvedAt(complaint.getResolvedAt());

        if (complaint.getRespondedBy() != null) {
            builder.respondedById(complaint.getRespondedBy().getId())
                    .respondedByName(complaint.getRespondedBy().getFirstname() + " " + complaint.getRespondedBy().getLastname());
        }

        return builder.build();
    }
}

