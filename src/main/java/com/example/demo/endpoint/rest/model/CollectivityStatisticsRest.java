package com.example.demo.endpoint.rest.model;

public class CollectivityStatisticsRest {

    private MemberDescriptionRest memberDescription;
    private Double earnedAmount;
    private Double unpaidAmount;
    private Double assiduityPercentage;

    public CollectivityStatisticsRest() {
    }

    public MemberDescriptionRest getMemberDescription() {
        return memberDescription;
    }

    public void setMemberDescription(MemberDescriptionRest memberDescription) {
        this.memberDescription = memberDescription;
    }

    public Double getEarnedAmount() {
        return earnedAmount;
    }

    public void setEarnedAmount(Double earnedAmount) {
        this.earnedAmount = earnedAmount;
    }

    public Double getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public Double getAssiduityPercentage() {
        return assiduityPercentage;
    }

    public void setAssiduityPercentage(Double assiduityPercentage) {
        this.assiduityPercentage = assiduityPercentage;
    }
}