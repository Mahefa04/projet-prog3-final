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

    // Méthodes ajoutées pour le mapper
    public String getMemberId() {
        return this.memberDescription != null ? this.memberDescription.getMemberId() : null;
    }

    public String getFirstName() {
        return this.memberDescription != null ? this.memberDescription.getFirstName() : null;
    }

    public String getLastName() {
        return this.memberDescription != null ? this.memberDescription.getLastName() : null;
    }

    public Double getPotentialUnpaidAmount() {
        return this.unpaidAmount;
    }

    public void setMemberId(String memberId) {
        if (this.memberDescription == null) {
            this.memberDescription = new MemberDescription();
        }
        this.memberDescription.setMemberId(memberId);
    }

    public void setFirstName(String firstName) {
        if (this.memberDescription == null) {
            this.memberDescription = new MemberDescription();
        }
        this.memberDescription.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        if (this.memberDescription == null) {
            this.memberDescription = new MemberDescription();
        }
        this.memberDescription.setLastName(lastName);
    }

    public void setPotentialUnpaidAmount(Double potentialUnpaidAmount) {
        this.unpaidAmount = potentialUnpaidAmount;
    }
}