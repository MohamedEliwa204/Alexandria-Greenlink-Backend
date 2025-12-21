package com.greenlink.backend.features.wallet.dto;

import com.greenlink.backend.features.wallet.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private Double amount;
    private TransactionType type;
    private String description;
    private LocalDateTime transactionDate;
    private String route;
}
