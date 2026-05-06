package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.mapper.CollectivityActivityRestMapper;
import com.example.demo.endpoint.rest.model.CollectivityActivityRest;
import com.example.demo.endpoint.rest.model.CreateCollectivityActivityRest;
import com.example.demo.model.CollectivityActivity;
import com.example.demo.model.CreateCollectivityActivity;
import com.example.demo.service.CollectivityActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CollectivityActivityController {

    private final CollectivityActivityService collectivityActivityService;
    private final CollectivityActivityRestMapper collectivityActivityRestMapper;

    public CollectivityActivityController(
            CollectivityActivityService collectivityActivityService,
            CollectivityActivityRestMapper collectivityActivityRestMapper
    ) {
        this.collectivityActivityService = collectivityActivityService;
        this.collectivityActivityRestMapper = collectivityActivityRestMapper;
    }

    @PostMapping("/collectivities/{id}/activities")
    public List<CollectivityActivityRest> createActivities(
            @PathVariable String id,
            @RequestBody List<CreateCollectivityActivityRest> request
    ) {
        List<CreateCollectivityActivity> activities = new ArrayList<CreateCollectivityActivity>();
        int i;

        for (i = 0; i < request.size(); i++) {
            activities.add(collectivityActivityRestMapper.toDomain(request.get(i)));
        }

        List<CollectivityActivity> created = collectivityActivityService.createActivities(id, activities);

        List<CollectivityActivityRest> response = new ArrayList<CollectivityActivityRest>();

        for (i = 0; i < created.size(); i++) {
            response.add(collectivityActivityRestMapper.toRest(created.get(i)));
        }

        return response;
    }

    @GetMapping("/collectivities/{id}/activities")
    public List<CollectivityActivityRest> getActivities(@PathVariable String id) {
        List<CollectivityActivity> activities = collectivityActivityService.getActivities(id);
        List<CollectivityActivityRest> response = new ArrayList<CollectivityActivityRest>();

        int i;
        for (i = 0; i < activities.size(); i++) {
            response.add(collectivityActivityRestMapper.toRest(activities.get(i)));
        }

        return response;
    }
}