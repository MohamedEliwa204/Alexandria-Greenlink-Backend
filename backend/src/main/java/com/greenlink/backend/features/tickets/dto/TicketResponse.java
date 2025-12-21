package com.greenlink.backend.features.tickets.dto;

import com.greenlink.backend.features.tickets.model.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

    private Long id;



    private String description;

    private String status;

    private TicketCategory category;
//
//
//    private Long userId;
//
//    private String userEmail;
//
//    private String userName;

    private String fromPlace;

    private String toPlace;

    private Double price;

    private LocalDateTime createdAt;
}

