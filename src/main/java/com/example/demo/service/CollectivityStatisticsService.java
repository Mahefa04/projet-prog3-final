package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CollectivityStatistics;
import com.example.demo.repository.CollectivityStatisticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CollectivityStatisticsService {

    private final CollectivityStatisticsRepository collectivityStatisticsRepository;

    public CollectivityStatisticsService(CollectivityStatisticsRepository collectivityStatisticsRepository) {
        this.collectivityStatisticsRepository = collectivityStatisticsRepository;
    }

    public List<CollectivityStatistics> getStatisticsByCollectivity(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {
        if (from == null || to == null) {
            throw new BadRequestException("from and to are required");
        }

        if (from.isAfter(to)) {
            throw new BadRequestException("from must be before or equal to to");
        }

        if (!collectivityStatisticsRepository.collectivityExists(collectivityId)) {
            throw new NotFoundException("Collectivity not found: " + collectivityId);
        }

        return collectivityStatisticsRepository.findStatisticsByCollectivity(collectivityId, from, to);
    }
}