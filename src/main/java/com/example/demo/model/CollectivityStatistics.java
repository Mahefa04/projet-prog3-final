package com.example.demo.model;

public class CollectivityStatistics {

    private MemberDescription memberDescription;
    private Double earnedAmount;
    private Double unpaidAmount;
    private Double assiduityPercentage;

    public CollectivityStatistics() {
    }

    public MemberDescription getMemberDescription() {
        return memberDescription;
    }

    public Double getEarnedAmount() {
        return earnedAmount;
    }

    public Double getUnpaidAmount() {
        return unpaidAmount;
    }

    public Double getAssiduityPercentage() {
        return assiduityPercentage;
    }

    public void setMemberDescription(MemberDescription memberDescription) {
        this.memberDescription = memberDescription;
    }

    public void setEarnedAmount(Double earnedAmount) {
        this.earnedAmount = earnedAmount;
    }

    public void setUnpaidAmount(Double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public void setAssiduityPercentage(Double assiduityPercentage) {
        this.assiduityPercentage = assiduityPercentage;
    }
}