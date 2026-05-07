package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityStatisticsRest;
import com.example.demo.endpoint.rest.model.MemberDescriptionRest;
import com.example.demo.model.CollectivityStatistics;
import com.example.demo.model.MemberDescription;
import org.springframework.stereotype.Component;

@Component
public class CollectivityStatisticsRestMapper {

    public CollectivityStatisticsRest toRest(CollectivityStatistics domain) {
        CollectivityStatisticsRest rest = new CollectivityStatisticsRest();

        rest.setMemberDescription(toMemberDescriptionRest(domain.getMemberDescription()));
        rest.setEarnedAmount(domain.getEarnedAmount());
        rest.setUnpaidAmount(domain.getUnpaidAmount());
        rest.setAssiduityPercentage(domain.getAssiduityPercentage());

        return rest;
    }

    private MemberDescriptionRest toMemberDescriptionRest(MemberDescription domain) {
        if (domain == null) {
            return null;
        }

        MemberDescriptionRest rest = new MemberDescriptionRest();

        rest.setId(domain.getId());
        rest.setFirstName(domain.getFirstName());
        rest.setLastName(domain.getLastName());
        rest.setEmail(domain.getEmail());
        rest.setOccupation(domain.getOccupation());

        return rest;
    }
}