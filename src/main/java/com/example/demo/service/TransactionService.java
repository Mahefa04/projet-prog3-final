package com.example.demo.service;

import com.example.demo.model.Collectivity;
import com.example.demo.model.CollectivityTransaction;
import com.example.demo.model.FinancialAccount;
import com.example.demo.model.Member;
import com.example.demo.repository.CollectivityRepository;
import com.example.demo.repository.CollectivityTransactionRepositoryImpl;
import com.example.demo.repository.FinancialAccountRepositoryImpl;
import com.example.demo.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

public class TransactionService {

    private CollectivityTransactionRepositoryImpl transactionRepository;
    private CollectivityRepository collectivityRepository;
    private MemberRepository memberRepository;
    private FinancialAccountRepositoryImpl accountRepository;

    public TransactionService() {
        this.transactionRepository = new CollectivityTransactionRepositoryImpl();
        this.collectivityRepository = new CollectivityRepository();
        this.memberRepository = new MemberRepository();
        this.accountRepository = new FinancialAccountRepositoryImpl();
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