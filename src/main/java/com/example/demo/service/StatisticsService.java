package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Statistics;
import com.example.demo.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<Statistics> getGlobalStatistics(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new BadRequestException("from and to are required");
        }

        if (from.isAfter(to)) {
            throw new BadRequestException("from must be before or equal to to");
        }

        return statisticsRepository.findGlobalStatistics(from, to);
    }
}
