package com.example.demo.model;

public class CollectivityTransaction {

    private String id;
    private Member memberDebited;
    private FinancialAccount accountCredited;
    private Double amount;
    private String reason;

    public CollectivityTransaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMemberDebited() {
        return memberDebited;
    }

    public void setMemberDebited(Member memberDebited) {
        this.memberDebited = memberDebited;
    }

    public FinancialAccount getAccountCredited() {
        return accountCredited;
    }

    public void setAccountCredited(FinancialAccount accountCredited) {
        this.accountCredited = accountCredited;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}