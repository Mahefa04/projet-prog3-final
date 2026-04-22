package com.example.demo.model;

import java.time.LocalDate;
import java.util.Objects;

public class CreateMembershipFee {
    private LocalDate eligibleFrom;
    private Frequency frequency;
    private double amount;
    private String label;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateMembershipFee that = (CreateMembershipFee) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(eligibleFrom, that.eligibleFrom) && Objects.equals(frequency, that.frequency) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eligibleFrom, frequency, amount, label);
    }

    @Override
    public String toString() {
        return "CreateMembershipFee{" +
                "eligibleFrom=" + eligibleFrom +
                ", frequency=" + frequency +
                ", amount=" + amount +
                ", label='" + label + '\'' +
                '}';
    }
}
