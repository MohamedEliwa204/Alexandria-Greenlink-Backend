package com.greenlink.backend.features.wallet.dto;

import com.greenlink.backend.features.wallet.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Double amount;
    private TransactionType type;
    private String description;
    private String route; // Optional, for transport tickets
}

