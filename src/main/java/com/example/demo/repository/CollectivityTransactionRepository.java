package com.example.demo.repository;

import com.example.demo.model.CollectivityTransaction;
import java.time.LocalDate;
import java.util.List;

public interface CollectivityTransactionRepository {

    List<CollectivityTransaction> findByCollectivityIdAndTransactionDateBetween(
            String collectivityId,
            LocalDate from,
            LocalDate to);
}