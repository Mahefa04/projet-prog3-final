package com.example.demo.endpoint.rest.controller;

import com.example.demo.model.CollectivityStatistics;
import com.example.demo.service.CollectivityStatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CollectivityStatisticsController {

    private final CollectivityStatisticsService collectivityStatisticsService;

    public CollectivityStatisticsController(CollectivityStatisticsService collectivityStatisticsService) {
        this.collectivityStatisticsService = collectivityStatisticsService;
    }

    @GetMapping({
            "/collectivites/{id}/statistics",
            "/collectivities/{id}/statistics"
    })
    public List<CollectivityStatistics> getStatisticsByCollectivity(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return collectivityStatisticsService.getStatisticsByCollectivity(id, from, to);
    }
}