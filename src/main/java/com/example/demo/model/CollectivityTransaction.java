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

    public CollectivityTransaction() {}

    public CollectivityTransaction(String id, String collectivityId, String memberPaymentId, Double amount, String paymentMode, String accountCreditedId, LocalDate creationDate) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
    }

    public String getMemberPaymentId() {
        return memberPaymentId;
    }

    public void setMemberPaymentId(String memberPaymentId) {
        this.memberPaymentId = memberPaymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAccountCreditedId() {
        return accountCreditedId;
    }

    public void setAccountCreditedId(String accountCreditedId) {
        this.accountCreditedId = accountCreditedId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}