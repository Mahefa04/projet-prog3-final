package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

public class CollectivityActivity {
    private String id;
    private String collectivityId;
    private String label;
    private String activityType;
    private List<String> memberOccupationConcerned;
    private MonthlyRecurrenceRule recurrenceRule;
    private LocalDate executiveDate;

    public CollectivityActivity() {
    }

    public String getId() {
        return id;
    }

    public String getCollectivityId() {
        return collectivityId;
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

    public MonthlyRecurrenceRule getRecurrenceRule() {
        return recurrenceRule;
    }

    public LocalDate getExecutiveDate() {
        return executiveDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
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

    public void setRecurrenceRule(MonthlyRecurrenceRule recurrenceRule) {
        this.recurrenceRule = recurrenceRule;
    }

    public void setExecutiveDate(LocalDate executiveDate) {
        this.executiveDate = executiveDate;
    }
}