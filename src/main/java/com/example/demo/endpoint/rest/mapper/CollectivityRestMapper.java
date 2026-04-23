package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityInformationRest;
import com.example.demo.endpoint.rest.model.CollectivityRest;
import com.example.demo.endpoint.rest.model.CreateMembershipFeeRest;
import com.example.demo.endpoint.rest.model.MembershipFeeRest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import org.springframework.stereotype.Component;

@Component
public class CollectivityRestMapper {

    public CollectivityRest toRest(Collectivity domain) {
        CollectivityRest rest = new CollectivityRest();

        rest.setId(domain.getId());
        rest.setNumber(domain.getNumber());
        rest.setName(domain.getName());
        rest.setLocality(domain.getLocality());
        rest.setSpecialization(domain.getSpecialization());

        return rest;
    }

    public CollectivityInformation toDomainInformation(CollectivityInformationRest request) {
        CollectivityInformation info = new CollectivityInformation();

        info.setName(request.getName());
        info.setNumber(request.getNumber());

        return info;
    }

    public CreateMembershipFee toDomainCreateMembershipFee(CreateMembershipFeeRest request) {
        CreateMembershipFee fee = new CreateMembershipFee();
        String frequencyValue;

        fee.setEligibleFrom(request.getEligibleFrom());
        fee.setAmount(request.getAmount());
        fee.setLabel(request.getLabel());

        frequencyValue = String.valueOf(request.getFrequency());

        if (frequencyValue == null) {
            throw new BadRequestException("Invalid frequency: null");
        }

        frequencyValue = frequencyValue.trim().toUpperCase();

        if ("WEEKLY".equals(frequencyValue)) {
            fee.setFrequency(Frequency.WEEKLY);
        } else if ("MONTHLY".equals(frequencyValue)) {
            fee.setFrequency(Frequency.MONTHLY);
        } else if ("ANNUALLY".equals(frequencyValue)) {
            fee.setFrequency(Frequency.ANNUALLY);
        } else if ("PUNCTUALLY".equals(frequencyValue)) {
            fee.setFrequency(Frequency.PUNCTUALLY);
        } else {
            throw new BadRequestException("Invalid frequency: " + request.getFrequency());
        }

        return fee;
    }

    public MembershipFeeRest toMembershipFeeRest(MembershipFee fee) {
        MembershipFeeRest rest = new MembershipFeeRest();

        rest.setId(fee.getId());
        rest.setEligibleFrom(fee.getEligibleFrom().toString());
        rest.setFrequency(com.example.demo.endpoint.rest.model.Frequency.valueOf(fee.getFrequency().name()));
        rest.setAmount(fee.getAmount());
        rest.setLabel(fee.getLabel());
        rest.setStatus(ActivityStatus.valueOf(fee.getStatus().name()));

        return rest;
    }
}