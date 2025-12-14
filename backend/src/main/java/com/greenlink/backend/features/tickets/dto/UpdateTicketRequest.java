package com.greenlink.backend.features.tickets.dto;

import com.greenlink.backend.features.tickets.model.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketRequest {

    private String title;

    private String description;

    private String status;

    private TicketCategory category;

    private String location;

    private String fromPlace;

    private String toPlace;

    private Double price;
}

