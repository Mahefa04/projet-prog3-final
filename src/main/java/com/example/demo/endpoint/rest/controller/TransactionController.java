package com.example.demo.endpoint.rest.controller;

import com.example.demo.model.CollectivityTransaction;
import com.example.demo.service.TransactionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<CollectivityTransaction> getTransactionsByCollectivity(
            @RequestParam String collectivityId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        return transactionService.getTransactionsByCollectivity(collectivityId, from, to);
    }
}