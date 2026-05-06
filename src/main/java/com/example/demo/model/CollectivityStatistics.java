package com.example.demo.model;

public class CollectivityStatistics {

    private String memberId;
    private String firstName;
    private String lastName;
    private Double earnedAmount;
    private Double potentialUnpaidAmount;

    public CollectivityStatistics() {
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getEarnedAmount() {
        return earnedAmount;
    }

    public Double getPotentialUnpaidAmount() {
        return potentialUnpaidAmount;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEarnedAmount(Double earnedAmount) {
        this.earnedAmount = earnedAmount;
    }

    public void setPotentialUnpaidAmount(Double potentialUnpaidAmount) {
        this.potentialUnpaidAmount = potentialUnpaidAmount;
    }
}