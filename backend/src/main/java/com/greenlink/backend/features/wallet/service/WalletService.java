package com.greenlink.backend.features.wallet.service;

import com.greenlink.backend.features.auth.model.User;
import com.greenlink.backend.features.auth.repository.UserRepository;
import com.greenlink.backend.features.wallet.dto.PaymentRequest;
import com.greenlink.backend.features.wallet.dto.TopUpRequest;
import com.greenlink.backend.features.wallet.dto.TransactionResponse;
import com.greenlink.backend.features.wallet.dto.WalletResponse;
import com.greenlink.backend.features.wallet.model.Transaction;
import com.greenlink.backend.features.wallet.model.TransactionType;
import com.greenlink.backend.features.wallet.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Double getBalance() {
        User user = getCurrentUser();
        return user.getBalance();
    }

    @Transactional
    public WalletResponse topUp(TopUpRequest request) {
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        User user = getCurrentUser();
        user.setBalance(user.getBalance() + request.getAmount());
        userRepository.save(user);

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .user(user)
                .amount(request.getAmount())
                .type(TransactionType.WALLET_TOP_UP)
                .description("Wallet Top-up")
                .transactionDate(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);

        return WalletResponse.builder()
                .balance(user.getBalance())
                .message("Top-up successful")
                .build();
    }

    @Transactional
    public WalletResponse makePayment(PaymentRequest request) {
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        User user = getCurrentUser();

        // Check if user has sufficient balance
        if (user.getBalance() < request.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Deduct amount from balance
        user.setBalance(user.getBalance() - request.getAmount());
        userRepository.save(user);

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .user(user)
                .amount(-request.getAmount()) // Negative for deductions
                .type(request.getType())
                .description(request.getDescription())
                .route(request.getRoute())
                .transactionDate(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);

        return WalletResponse.builder()
                .balance(user.getBalance())
                .message("Payment successful")
                .build();
    }

    public List<TransactionResponse> getTransactions() {
        User user = getCurrentUser();
        List<Transaction> transactions = transactionRepository.findByUserIdOrderByTransactionDateDesc(user.getId());

        return transactions.stream()
                .map(t -> TransactionResponse.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .type(t.getType())
                        .description(t.getDescription())
                        .transactionDate(t.getTransactionDate())
                        .route(t.getRoute())
                        .build())
                .collect(Collectors.toList());
    }
}

