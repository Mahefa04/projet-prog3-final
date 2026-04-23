package com.example.demo.repository;

import com.example.demo.model.CollectivityTransaction;
import java.time.LocalDate;
import java.util.List;

public interface CollectivityTransactionRepository {

    void save(CollectivityTransaction transaction);

    List<CollectivityTransaction> findByCollectivityIdAndDateRange(
            String collectivityId,
            LocalDate from,
            LocalDate to
    );
}