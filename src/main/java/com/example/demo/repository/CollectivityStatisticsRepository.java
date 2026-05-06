package com.example.demo.repository;

import com.example.demo.model.CollectivityStatistics;

import java.time.LocalDate;
import java.util.List;

public interface CollectivityStatisticsRepository {

    boolean collectivityExists(String collectivityId);

    List<CollectivityStatistics> findLocalStatistics(
            String collectivityId,
            LocalDate from,
            LocalDate to
    );
}