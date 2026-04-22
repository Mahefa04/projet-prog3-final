package com.example.demo.endpoint.rest.model;

import com.example.demo.model.Frequency;

import java.time.LocalDate;

public class CreateMembershipFeeRest {
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private double amount;
    private String label;

    public CreateMembershipFeeRest(LocalDate eligibleFrom, Frequency frequency, double amount, String label) {
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
    }

    public LocalDate getEligibleFrom() {
        return eligibleFrom;
    }

    public void setEligibleFrom(LocalDate eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
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
}
