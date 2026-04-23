package com.example.demo.service;

import com.example.demo.model.MemberPayment;
import com.example.demo.repository.CollectivityTransactionRepository;
import com.example.demo.repository.FinancialAccountRepository;
import com.example.demo.repository.MemberPaymentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MembershipFeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    private final MemberRepository memberRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final FinancialAccountRepository accountRepository;
    private final MemberPaymentRepository paymentRepository;
    private final CollectivityTransactionRepository transactionRepository;

    public PaymentService(
            MemberRepository memberRepository,
            MembershipFeeRepository membershipFeeRepository,
            FinancialAccountRepository accountRepository,
            MemberPaymentRepository paymentRepository,
            CollectivityTransactionRepository transactionRepository
    ) {
        this.memberRepository = memberRepository;
        this.membershipFeeRepository = membershipFeeRepository;
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<MemberPayment> createPayments(String memberId, List<MemberPayment> payments) {
        LocalDate today = LocalDate.now();

        for (MemberPayment payment : payments) {
            payment.setMemberId(memberId);
            payment.setPaymentDate(today);
        }

        return paymentRepository.saveAll(payments);
    }
}