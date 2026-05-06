package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityLocalStatisticsRest;
import com.example.demo.model.CollectivityLocalStatistics;
import org.springframework.stereotype.Component;

@Component
public class CollectivityStatisticsRestMapper {

    public CollectivityLocalStatisticsRest toRest(CollectivityLocalStatistics domain) {
        CollectivityLocalStatisticsRest rest = new CollectivityLocalStatisticsRest();

        rest.setMemberId(domain.getMemberId());
        rest.setFirstName(domain.getFirstName());
        rest.setLastName(domain.getLastName());
        rest.setEarnedAmount(domain.getEarnedAmount());
        rest.setPotentialUnpaidAmount(domain.getPotentialUnpaidAmount());

        return rest;
    }
}