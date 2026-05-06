package com.example.demo.service;

import com.example.demo.repository.StatisticsRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public List<Map<String, Object>> getAllStatistics(LocalDate from, LocalDate to) {
        List<Map<String, Object>> responses = new ArrayList<>();
        
        if (from == null || to == null) {
            throw new IllegalArgumentException("Les paramètres 'from' et 'to' sont obligatoires");
        }
        
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("La date 'from' doit être antérieure à 'to'");
        }
        
        List<Map<String, Object>> collectivities = statisticsRepository.getAllCollectivities();
        
        for (Map<String, Object> collectivity : collectivities) {
            String id = (String) collectivity.get("id");
            String name = (String) collectivity.get("name");
            
            int totalMembers = statisticsRepository.getTotalMembersByCollectivity(id);
            int membersUpToDate = statisticsRepository.getMembersUpToDateCount(id, from, to);
            int newMembersCount = statisticsRepository.getNewMembersCountByCollectivity(id, from, to);
            
            double percentageUpToDate = totalMembers > 0 ? (membersUpToDate * 100.0 / totalMembers) : 0.0;
            
            Map<String, Object> response = new HashMap<>();
            response.put("collectivityId", id);
            response.put("collectivityName", name != null ? name : id);
            response.put("percentageUpToDate", Math.round(percentageUpToDate * 100.0) / 100.0);
            response.put("newMembersCount", newMembersCount);
            response.put("totalMembers", totalMembers);
            response.put("membersUpToDate", membersUpToDate);
            
            responses.add(response);
        }
        
        return responses;
    }
}