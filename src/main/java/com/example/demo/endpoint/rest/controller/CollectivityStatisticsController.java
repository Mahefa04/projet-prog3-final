package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.mapper.CollectivityStatisticsRestMapper;
import com.example.demo.endpoint.rest.model.CollectivityLocalStatisticsRest;
import com.example.demo.model.CollectivityLocalStatistics;
import com.example.demo.service.CollectivityStatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CollectivityStatisticsController {

    private final CollectivityStatisticsService collectivityStatisticsService;
    private final CollectivityStatisticsRestMapper collectivityStatisticsRestMapper;

    public CollectivityStatisticsController(
            CollectivityStatisticsService collectivityStatisticsService,
            CollectivityStatisticsRestMapper collectivityStatisticsRestMapper
    ) {
        this.collectivityStatisticsService = collectivityStatisticsService;
        this.collectivityStatisticsRestMapper = collectivityStatisticsRestMapper;
    }

    @GetMapping("/collectivities/{id}/statistics")
    public List<CollectivityLocalStatisticsRest> getLocalStatistics(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<CollectivityLocalStatistics> statistics;
        List<CollectivityLocalStatisticsRest> response;
        int i;

        statistics = collectivityStatisticsService.getLocalStatistics(id, from, to);

        response = new ArrayList<CollectivityLocalStatisticsRest>();

        for (i = 0; i < statistics.size(); i++) {
            response.add(collectivityStatisticsRestMapper.toRest(statistics.get(i)));
        }

        return response;
    }
}