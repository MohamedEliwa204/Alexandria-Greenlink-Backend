package com.greenlink.backend.features.tickets.dto;

import com.greenlink.backend.features.tickets.model.TicketCategory;
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
public class CreateTicketRequest {



    private String description;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Category is required")
    private TicketCategory category;


    @NotBlank(message = "From place is required")
    private String fromPlace;

    @NotBlank(message = "To place is required")
    private String toPlace;

    @NotNull(message = "Where is the price")
    private Double price;
}


