package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentService {

    private MemberRepository memberRepository;
    private MembershipFeeRepository membershipFeeRepository;
    private FinancialAccountRepositoryImpl accountRepository;
    private MemberPaymentRepositoryImpl paymentRepository;
    private CollectivityTransactionRepositoryImpl transactionRepository;

    public PaymentService() {
        this.memberRepository = new MemberRepository();
        this.membershipFeeRepository = new MembershipFeeRepository();
        this.accountRepository = new FinancialAccountRepositoryImpl();
        this.paymentRepository = new MemberPaymentRepositoryImpl();
        this.transactionRepository = new CollectivityTransactionRepositoryImpl();
    }

    public List<MemberPayment> createPayments(String memberId, List<MemberPayment> payments) throws Exception {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new Exception("Member not found");
        }

        List<MemberPayment> createdPayments = new ArrayList<>();

        for (MemberPayment payment : payments) {
            MembershipFee membershipFee = membershipFeeRepository.findById(payment.getMembershipFeeId());
            if (membershipFee == null) {
                throw new Exception("Membership fee not found: " + payment.getMembershipFeeId());
            }

            FinancialAccount account = accountRepository.findById(payment.getAccountCreditedId());
            if (account == null) {
                throw new Exception("Financial account not found: " + payment.getAccountCreditedId());
            }

            if (payment.getAmount() <= 0) {
                throw new Exception("Amount must be greater than 0");
            }

            String paymentId = "PAY" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            MemberPayment newPayment = new MemberPayment();
            newPayment.setId(paymentId);
            newPayment.setMemberId(memberId);
            newPayment.setMembershipFeeId(membershipFee.getId());
            newPayment.setAmount(payment.getAmount());
            newPayment.setPaymentMode(payment.getPaymentMode());
            newPayment.setAccountCreditedId(account.getId());
            newPayment.setCreationDate(LocalDate.now());

            paymentRepository.save(newPayment);

            account.setAmount(account.getAmount() + payment.getAmount());
            accountRepository.updateAmount(account.getId(), account.getAmount());

            String transactionId = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            CollectivityTransaction transaction = new CollectivityTransaction();
            transaction.setId(transactionId);
            transaction.setCollectivityId(member.getCollectivityId());
            transaction.setMemberPaymentId(paymentId);
            transaction.setAmount(Double.valueOf(payment.getAmount()));
            transaction.setPaymentMode(payment.getPaymentMode());
            transaction.setAccountCreditedId(account.getId());
            transaction.setCreationDate(LocalDate.now());

            transactionRepository.save(transaction);

            createdPayments.add(newPayment);
        }

        return createdPayments;
    }
}
