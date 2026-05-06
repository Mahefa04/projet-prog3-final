package com.example.demo.endpoint.rest.model;

import java.time.LocalDate;
import java.util.List;

public class CollectivityActivityRest {
    private String id;
    private String label;
    private String activityType;
    private List<String> memberOccupationConcerned;
    private MonthlyRecurrenceRuleRest recurrenceRule;
    private LocalDate executiveDate;

    public CollectivityActivityRest() {
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getActivityType() {
        return activityType;
    }

    public List<String> getMemberOccupationConcerned() {
        return memberOccupationConcerned;
    }

    public MonthlyRecurrenceRuleRest getRecurrenceRule() {
        return recurrenceRule;
    }

    public LocalDate getExecutiveDate() {
        return executiveDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public void setMemberOccupationConcerned(List<String> memberOccupationConcerned) {
        this.memberOccupationConcerned = memberOccupationConcerned;
    }

    public void setRecurrenceRule(MonthlyRecurrenceRuleRest recurrenceRule) {
        this.recurrenceRule = recurrenceRule;
    }

    public void setExecutiveDate(LocalDate executiveDate) {
        this.executiveDate = executiveDate;
    }
}