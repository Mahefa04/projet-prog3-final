package com.example.demo.endpoint.rest.model;

import com.example.demo.model.ActivityStatus;

public class MembershipFeeRest {
    private String id;
    private ActivityStatus status;
    private Frequency frequency;
    private double amount;
    private String label;
    private String eligibleFrom;

    public MembershipFeeRest(String id, ActivityStatus status, Frequency frequency, double amount, String label, String eligibleFrom) {
        this.id = id;
        this.status = status;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
        this.eligibleFrom = eligibleFrom;
    }

    public MembershipFeeRest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getEligibleFrom() {
        return eligibleFrom;
    }

    public void setEligibleFrom(String eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
    }
}

