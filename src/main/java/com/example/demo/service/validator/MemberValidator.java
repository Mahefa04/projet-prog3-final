package com.example.demo.service.validator;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Collectivity;
import com.example.demo.model.CreateMember;
import com.example.demo.model.Member;
import com.example.demo.model.MemberOccupation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberValidator {

    public void validate(CreateMember input, Collectivity targetCollectivity, List<Member> referees) {
        validatePayments(input);
        validateReferees(targetCollectivity, referees);
    }

    private void validatePayments(CreateMember input) {
        if (input.getRegistrationFeePaid() == null || !input.getRegistrationFeePaid()) {
            throw new BadRequestException("Registration fee not paid");
        }

        if (input.getMembershipDuesPaid() == null || !input.getMembershipDuesPaid()) {
            throw new BadRequestException("Membership dues not paid");
        }
    }

    private void validateReferees(Collectivity targetCollectivity, List<Member> referees) {
        if (referees == null || referees.size() < 2) {
            throw new BadRequestException("At least two referees are required");
        }

        int confirmedCount = 0;
        int sameCollectivityCount = 0;
        int otherCollectivitiesCount = 0;

        for (Member referee : referees) {
            if (isConfirmedMember(referee)) {
                confirmedCount++;

                if (referee.getCollectivity() != null
                        && referee.getCollectivity().getId() != null
                        && referee.getCollectivity().getId().equals(targetCollectivity.getId())) {
                    sameCollectivityCount++;
                } else {
                    otherCollectivitiesCount++;
                }
            }
        }

        if (confirmedCount < 2) {
            throw new BadRequestException("At least two confirmed referees are required");
        }

        if (sameCollectivityCount < otherCollectivitiesCount) {
            throw new BadRequestException(
                    "Referees from target collectivity must be at least equal to referees from other collectivities"
            );
        }
    }

    private boolean isConfirmedMember(Member member) {
        return member.getOccupation("occupation") != MemberOccupation.JUNIOR;
    }
}