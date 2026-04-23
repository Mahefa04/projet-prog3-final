package com.example.demo.repository;

import com.example.demo.model.FinancialAccount;
import java.time.LocalDate;
import java.util.List;

public interface FinancialAccountRepository {

    FinancialAccount findById(String id);

    void updateAmount(String id, Double newAmount);

    List<FinancialAccount> findByCollectivityId(String collectivityId);

    List<FinancialAccount> findByCollectivityIdAtDate(String collectivityId, LocalDate at);
}