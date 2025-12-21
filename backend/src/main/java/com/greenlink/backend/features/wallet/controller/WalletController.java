package com.greenlink.backend.features.wallet.controller;

import com.greenlink.backend.features.wallet.dto.PaymentRequest;
import com.greenlink.backend.features.wallet.dto.TopUpRequest;
import com.greenlink.backend.features.wallet.dto.TransactionResponse;
import com.greenlink.backend.features.wallet.dto.WalletResponse;
import com.greenlink.backend.features.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallet")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(walletService.getBalance());
    }

    @PostMapping("/top-up")
    public ResponseEntity<WalletResponse> topUp(@RequestBody TopUpRequest request) {
        return ResponseEntity.ok(walletService.topUp(request));
    }

    @PostMapping("/payment")
    public ResponseEntity<WalletResponse> makePayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(walletService.makePayment(request));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions() {
        return ResponseEntity.ok(walletService.getTransactions());
    }
}
