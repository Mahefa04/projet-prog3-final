package com.example.demo.service;

import com.example.demo.model.CollectivityTransaction;
import com.example.demo.repository.CollectivityTransactionRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final CollectivityTransactionRepository transactionRepository;

    public TransactionService(CollectivityTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<CollectivityTransaction> getTransactionsByCollectivity(
            String collectivityId,
            LocalDate from,
            LocalDate to) {

        return transactionRepository.findByCollectivityIdAndTransactionDateBetween(
                collectivityId, from, to);
    }
}