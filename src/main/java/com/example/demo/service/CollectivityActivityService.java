package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CollectivityActivity;
import com.example.demo.model.CreateCollectivityActivity;
import com.example.demo.repository.CollectivityActivityRepository;
import com.example.demo.service.validator.CollectivityActivityValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CollectivityActivityService {

    private final CollectivityActivityRepository collectivityActivityRepository;
    private final CollectivityActivityValidator collectivityActivityValidator;

    public CollectivityActivityService(
            CollectivityActivityRepository collectivityActivityRepository,
            CollectivityActivityValidator collectivityActivityValidator
    ) {
        this.collectivityActivityRepository = collectivityActivityRepository;
        this.collectivityActivityValidator = collectivityActivityValidator;
    }

    public List<CollectivityActivity> createActivities(
            String collectivityId,
            List<CreateCollectivityActivity> createActivities
    ) {
        if (!collectivityActivityRepository.collectivityExists(collectivityId)) {
            throw new NotFoundException("Collectivity not found: " + collectivityId);
        }

        collectivityActivityValidator.validateAll(createActivities);

        List<CollectivityActivity> createdActivities = new ArrayList<CollectivityActivity>();

        int i;
        for (i = 0; i < createActivities.size(); i++) {
            CollectivityActivity activity = new CollectivityActivity();

            activity.setId("ACT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            activity.setCollectivityId(collectivityId);
            activity.setLabel(createActivities.get(i).getLabel());
            activity.setActivityType(createActivities.get(i).getActivityType());
            activity.setMemberOccupationConcerned(createActivities.get(i).getMemberOccupationConcerned());
            activity.setExecutiveDate(createActivities.get(i).getExecutiveDate());
            activity.setRecurrenceRule(createActivities.get(i).getRecurrenceRule());

            createdActivities.add(collectivityActivityRepository.save(collectivityId, activity));
        }

        return createdActivities;
    }

    public List<CollectivityActivity> getActivities(String collectivityId) {
        if (!collectivityActivityRepository.collectivityExists(collectivityId)) {
            throw new NotFoundException("Collectivity not found: " + collectivityId);
        }

        return collectivityActivityRepository.findByCollectivityId(collectivityId);
    }
}