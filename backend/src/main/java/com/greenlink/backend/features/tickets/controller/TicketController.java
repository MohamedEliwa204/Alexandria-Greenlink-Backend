package com.greenlink.backend.features.tickets.controller;

import com.greenlink.backend.features.tickets.dto.CreateTicketRequest;
import com.greenlink.backend.features.tickets.dto.TicketResponse;
import com.greenlink.backend.features.tickets.dto.UpdateTicketRequest;
import com.greenlink.backend.features.tickets.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    // Create a new ticket
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @Valid @RequestBody CreateTicketRequest request,
            Authentication authentication
    ) {
        String userEmail = authentication.getName(); // Extract email from JWT token
        TicketResponse response = ticketService.createTicket(request, userEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all tickets for the authenticated user
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getUserTickets(Authentication authentication) {
        String userEmail = authentication.getName();
        List<TicketResponse> tickets = ticketService.getUserTickets(userEmail);
        return ResponseEntity.ok(tickets);
    }

    // Get a specific ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        TicketResponse ticket = ticketService.getTicketById(id, userEmail);
        return ResponseEntity.ok(ticket);
    }

    // Update a ticket
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTicketRequest request,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        TicketResponse updatedTicket = ticketService.updateTicket(id, request, userEmail);
        return ResponseEntity.ok(updatedTicket);
    }

    // Delete a ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        ticketService.deleteTicket(id, userEmail);
        return ResponseEntity.noContent().build();
    }

    // Search tickets by status
    @GetMapping("/search/status")
    public ResponseEntity<List<TicketResponse>> searchByStatus(
            @RequestParam String status,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        List<TicketResponse> tickets = ticketService.searchByStatus(status, userEmail);
        return ResponseEntity.ok(tickets);
    }

    // Search tickets by fromPlace
    @GetMapping("/search/from")
    public ResponseEntity<List<TicketResponse>> searchByFromPlace(
            @RequestParam String place,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        List<TicketResponse> tickets = ticketService.searchByFromPlace(place, userEmail);
        return ResponseEntity.ok(tickets);
    }

    // Search tickets by toPlace
    @GetMapping("/search/to")
    public ResponseEntity<List<TicketResponse>> searchByToPlace(
            @RequestParam String place,
            Authentication authentication
    ) {
        String userEmail = authentication.getName();
        List<TicketResponse> tickets = ticketService.searchByToPlace(place, userEmail);
        return ResponseEntity.ok(tickets);
    }
}

