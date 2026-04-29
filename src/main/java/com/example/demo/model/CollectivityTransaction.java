package com.example.demo.model;

import java.time.LocalDate;

public class CollectivityTransaction {

    private String id;
    private String collectivityId;
    private String memberPaymentId;
    private Double amount;
    private String paymentMode;
    private String accountCreditedId;
    private LocalDate creationDate;

    public CollectivityTransaction() {
    }

    public CollectivityTransaction(
            String id,
            String collectivityId,
            String memberPaymentId,
            Double amount,
            String paymentMode,
            String accountCreditedId,
            LocalDate creationDate
    ) {
        this.id = id;
        this.collectivityId = collectivityId;
        this.memberPaymentId = memberPaymentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.accountCreditedId = accountCreditedId;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public String getMemberPaymentId() {
        return memberPaymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getAccountCreditedId() {
        return accountCreditedId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public void setMemberPaymentId(String memberPaymentId) {
        this.memberPaymentId = memberPaymentId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setAccountCreditedId(String accountCreditedId) {
        this.accountCreditedId = accountCreditedId;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}