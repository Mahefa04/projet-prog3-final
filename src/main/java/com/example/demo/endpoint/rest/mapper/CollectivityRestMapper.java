package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CollectivityInformationRest;
import com.example.demo.endpoint.rest.model.CreateMembershipFeeRest;
import com.example.demo.endpoint.rest.model.MembershipFeeRest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CollectivityInformation;
import com.example.demo.model.CreateMembershipFee;
import com.example.demo.model.Frequency;
import com.example.demo.model.MembershipFee;
import org.springframework.stereotype.Component;

@Component
public class CollectivityRestMapper {

    public CollectivityInformation toDomainInformation(CollectivityInformationRest rest) {
        CollectivityInformation info = new CollectivityInformation();

        info.setName(rest.getName());
        info.setNumber(rest.getNumber());

        return info;
    }

    public CreateMembershipFee toDomainCreateMembershipFee(CreateMembershipFeeRest rest) {
        CreateMembershipFee fee = new CreateMembershipFee();
        String frequencyValue;

        fee.setEligibleFrom(rest.getEligibleFrom());
        fee.setAmount(rest.getAmount());
        fee.setLabel(rest.getLabel());

        frequencyValue = rest.getFrequency();
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
            throw new BadRequestException("Invalid frequency: " + rest.getFrequency());
        }

        return fee;
    }

    public MembershipFeeRest toMembershipFeeRest(MembershipFee fee) {
        MembershipFeeRest rest = new MembershipFeeRest();

        rest.setId(fee.getId());
        rest.setEligibleFrom(fee.getEligibleFrom().toString());
        rest.setFrequency(fee.getFrequency().name());
        rest.setAmount(fee.getAmount());
        rest.setLabel(fee.getLabel());
        rest.setStatus(fee.getStatus().name());

        return rest;
    }
}