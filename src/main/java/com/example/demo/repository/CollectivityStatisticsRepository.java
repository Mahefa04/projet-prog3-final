package com.example.demo.repository;

import com.example.demo.model.CollectivityLocalStatistics;

import java.time.LocalDate;
import java.util.List;

public interface CollectivityStatisticsRepository {

    boolean collectivityExists(String collectivityId);

    List<CollectivityLocalStatistics> findLocalStatistics(
            String collectivityId,
            LocalDate from,
            LocalDate to
    );
}