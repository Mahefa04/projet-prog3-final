package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityActivityRest;
import com.example.demo.endpoint.rest.model.CreateCollectivityActivityRest;
import com.example.demo.endpoint.rest.model.MonthlyRecurrenceRuleRest;
import com.example.demo.model.CollectivityActivity;
import com.example.demo.model.CreateCollectivityActivity;
import com.example.demo.model.MonthlyRecurrenceRule;
import org.springframework.stereotype.Component;

@Component
public class CollectivityActivityRestMapper {

    public CreateCollectivityActivity toDomain(CreateCollectivityActivityRest rest) {
        CreateCollectivityActivity domain = new CreateCollectivityActivity();

        domain.setLabel(rest.getLabel());
        domain.setActivityType(rest.getActivityType());
        domain.setMemberOccupationConcerned(rest.getMemberOccupationConcerned());
        domain.setExecutiveDate(rest.getExecutiveDate());

        if (rest.getRecurrenceRule() != null) {
            MonthlyRecurrenceRule rule = new MonthlyRecurrenceRule();
            rule.setWeekOrdinal(rest.getRecurrenceRule().getWeekOrdinal());
            rule.setDayOfWeek(rest.getRecurrenceRule().getDayOfWeek());
            domain.setRecurrenceRule(rule);
        }

        return domain;
    }

    public CollectivityActivityRest toRest(CollectivityActivity domain) {
        CollectivityActivityRest rest = new CollectivityActivityRest();

        rest.setId(domain.getId());
        rest.setLabel(domain.getLabel());
        rest.setActivityType(domain.getActivityType());
        rest.setMemberOccupationConcerned(domain.getMemberOccupationConcerned());
        rest.setExecutiveDate(domain.getExecutiveDate());

        if (domain.getRecurrenceRule() != null) {
            MonthlyRecurrenceRuleRest ruleRest = new MonthlyRecurrenceRuleRest();
            ruleRest.setWeekOrdinal(domain.getRecurrenceRule().getWeekOrdinal());
            ruleRest.setDayOfWeek(domain.getRecurrenceRule().getDayOfWeek());
            rest.setRecurrenceRule(ruleRest);
        }

        return rest;
    }
}