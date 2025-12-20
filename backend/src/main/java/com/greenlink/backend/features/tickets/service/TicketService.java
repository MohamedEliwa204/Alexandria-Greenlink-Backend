package com.greenlink.backend.features.tickets.service;

import com.greenlink.backend.features.auth.model.User;
import com.greenlink.backend.features.auth.repository.UserRepository;
import com.greenlink.backend.features.tickets.dto.CreateTicketRequest;
import com.greenlink.backend.features.tickets.dto.TicketResponse;
import com.greenlink.backend.features.tickets.dto.UpdateTicketRequest;
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
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    // Create a new ticket
    public TicketResponse createTicket(CreateTicketRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ticket ticket = Ticket.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .category(request.getCategory())
                .location(request.getLocation())
                .user(user)
                .fromPlace(request.getFromPlace())
                .toPlace(request.getToPlace())
                .price(request.getPrice())
                .createdAt(LocalDateTime.now())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapToResponse(savedTicket);
    }

    // Get all tickets for the authenticated user
    public List<TicketResponse> getUserTickets(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUserId(user.getId());
        return tickets.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Get a specific ticket by ID (only if it belongs to the user)
    public TicketResponse getTicketById(Long ticketId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if the ticket belongs to the user
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Ticket does not belong to user");
        }

        return mapToResponse(ticket);
    }

    // Update a ticket
    public TicketResponse updateTicket(Long ticketId, UpdateTicketRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if the ticket belongs to the user
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Ticket does not belong to user");
        }

        // Update fields only if they are provided
        if (request.getTitle() != null) {
            ticket.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            ticket.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            ticket.setStatus(request.getStatus());
        }
        if (request.getCategory() != null) {
            ticket.setCategory(request.getCategory());
        }
        if (request.getLocation() != null) {
            ticket.setLocation(request.getLocation());
        }
        if (request.getFromPlace() != null) {
            ticket.setFromPlace(request.getFromPlace());
        }
        if (request.getToPlace() != null) {
            ticket.setToPlace(request.getToPlace());
        }
        if (request.getPrice() != null) {
            ticket.setPrice(request.getPrice());
        }

        Ticket updatedTicket = ticketRepository.save(ticket);
        return mapToResponse(updatedTicket);
    }

    // Delete a ticket
    public void deleteTicket(Long ticketId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        // Check if the ticket belongs to the user
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized: Ticket does not belong to user");
        }

        ticketRepository.delete(ticket);
    }

    // Search tickets by status
    public List<TicketResponse> searchByStatus(String status, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUserIdAndStatus(user.getId(), status);
        return tickets.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Search tickets by fromPlace
    public List<TicketResponse> searchByFromPlace(String fromPlace, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUserIdAndFromPlaceContaining(user.getId(), fromPlace);
        return tickets.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Search tickets by toPlace
    public List<TicketResponse> searchByToPlace(String toPlace, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUserIdAndToPlaceContaining(user.getId(), toPlace);
        return tickets.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Helper method to map Ticket entity to TicketResponse DTO
    private TicketResponse mapToResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .category(ticket.getCategory())
                .location(ticket.getLocation())
                .userId(ticket.getUser().getId())
                .userEmail(ticket.getUser().getEmail())
                .userName(ticket.getUser().getUsername())
                .fromPlace(ticket.getFromPlace())
                .toPlace(ticket.getToPlace())
                .price(ticket.getPrice())
                .createdAt(ticket.getCreatedAt())
                .build();
    }
}

