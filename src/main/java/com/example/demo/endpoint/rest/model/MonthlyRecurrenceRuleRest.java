package com.example.demo.endpoint.rest.model;

public class MonthlyRecurrenceRuleRest {
    private Integer weekOrdinal;
    private String dayOfWeek;

    public MonthlyRecurrenceRuleRest() {
    }

    public Integer getWeekOrdinal() {
        return weekOrdinal;
    }

    public void setWeekOrdinal(Integer weekOrdinal) {
        this.weekOrdinal = weekOrdinal;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}