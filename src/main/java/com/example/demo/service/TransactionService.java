package com.example.demo.service;

import com.example.demo.model.Collectivity;
import com.example.demo.model.CollectivityTransaction;
import com.example.demo.model.FinancialAccount;
import com.example.demo.model.Member;
import com.example.demo.repository.CollectivityRepository;
import com.example.demo.repository.CollectivityTransactionRepository;
import com.example.demo.repository.FinancialAccountRepository;
import com.example.demo.repository.MemberRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {

    private CollectivityTransactionRepository transactionRepository;
    private CollectivityRepository collectivityRepository;
    private MemberRepository memberRepository;
    private FinancialAccountRepository accountRepository;

    public TransactionService() {
        this.transactionRepository = new CollectivityTransactionRepository();
        this.collectivityRepository = new CollectivityRepository();
        this.memberRepository = new MemberRepository();
        this.accountRepository = new FinancialAccountRepository();
    }

    public List<CollectivityTransaction> getTransactionsByCollectivity(String collectivityId, LocalDate from, LocalDate to) throws Exception {
        Collectivity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new Exception("Collectivity not found");
        }

        if (from == null || to == null) {
            throw new Exception("Query parameters 'from' and 'to' are mandatory");
        }

        if (from.isAfter(to)) {
            throw new Exception("'from' date must be before 'to' date");
        }

        List<CollectivityTransaction> transactions = transactionRepository.findByCollectivityIdAndDateRange(collectivityId, from, to);

        for (CollectivityTransaction transaction : transactions) {
            Member member = memberRepository.findById(transaction.getMemberPaymentId());
            FinancialAccount account = accountRepository.findById(transaction.getAccountCreditedId());
            transaction.setMemberDebited(member);
            transaction.setAccountCredited(account);
        }

        return transactions;
    }
}