package com.example.demo.service.validator;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.CreateMembershipFee;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MembershipFeeValidator {

    public void validateAll(List<CreateMembershipFee> fees) {
        if (fees == null || fees.isEmpty()) {
            throw new BadRequestException("Membership fees are required");
        }

        for (CreateMembershipFee fee : fees) {
            validate(fee);
        }
    }

    private void validate(CreateMembershipFee fee) {
        if (fee == null) {
            throw new BadRequestException("Membership fee is required");
        }

        if (fee.getEligibleFrom() == null) {
            throw new BadRequestException("eligibleFrom is required");
        }

        if (fee.getFrequency() == null) {
            throw new BadRequestException("frequency is required");
        }

        if (fee.getAmount() < 0) {
            throw new BadRequestException("amount must be greater than or equal to 0");
        }

        if (fee.getLabel() == null || fee.getLabel().trim().isEmpty()) {
            throw new BadRequestException("label is required");
        }
    }
}