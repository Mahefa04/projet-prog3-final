package com.example.demo.service.validator;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CreateCollectivityActivity;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CollectivityActivityValidator {

    public void validateAll(List<CreateCollectivityActivity> activities) {
        if (activities == null || activities.isEmpty()) {
            throw new BadRequestException("Activities are required");
        }

        int i;
        for (i = 0; i < activities.size(); i++) {
            validate(activities.get(i));
        }
    }

    private void validate(CreateCollectivityActivity activity) {
        if (activity == null) {
            throw new BadRequestException("Activity is required");
        }

        if (activity.getLabel() == null || activity.getLabel().trim().isEmpty()) {
            throw new BadRequestException("Activity label is required");
        }

        if (activity.getActivityType() == null || activity.getActivityType().trim().isEmpty()) {
            throw new BadRequestException("Activity type is required");
        }

        if (!"MEETING".equals(activity.getActivityType())
                && !"TRAINING".equals(activity.getActivityType())
                && !"OTHER".equals(activity.getActivityType())) {
            throw new BadRequestException("Invalid activity type: " + activity.getActivityType());
        }

        if (activity.getExecutiveDate() != null && activity.getRecurrenceRule() != null) {
            throw new BadRequestException("executiveDate and recurrenceRule cannot be provided together");
        }

        if (activity.getExecutiveDate() == null && activity.getRecurrenceRule() == null) {
            throw new BadRequestException("Either executiveDate or recurrenceRule is required");
        }

        if (activity.getRecurrenceRule() != null) {
            if (activity.getRecurrenceRule().getWeekOrdinal() == null
                    || activity.getRecurrenceRule().getWeekOrdinal() < 1
                    || activity.getRecurrenceRule().getWeekOrdinal() > 5) {
                throw new BadRequestException("weekOrdinal must be between 1 and 5");
            }

            if (activity.getRecurrenceRule().getDayOfWeek() == null
                    || activity.getRecurrenceRule().getDayOfWeek().trim().isEmpty()) {
                throw new BadRequestException("dayOfWeek is required");
            }
        }
    }
}