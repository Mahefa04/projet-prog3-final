package com.example.demo.model;

import java.time.LocalDate;

public class MemberPayment {

    private String id;
    private String memberId;
    private String membershipFeeId;
    private Integer amount;
    private String paymentMode;
    private String accountCreditedId;
    private LocalDate creationDate;

    public MemberPayment() {
    }

    public MemberPayment(
            String id,
            String memberId,
            String membershipFeeId,
            Integer amount,
            String paymentMode,
            String accountCreditedId,
            LocalDate creationDate
    ) {
        this.id = id;
        this.memberId = memberId;
        this.membershipFeeId = membershipFeeId;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMembershipFeeId() {
        return membershipFeeId;
    }

    public void setMembershipFeeId(String membershipFeeId) {
        this.membershipFeeId = membershipFeeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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