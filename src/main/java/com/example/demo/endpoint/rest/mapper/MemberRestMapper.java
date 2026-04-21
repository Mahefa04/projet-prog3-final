package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.CreateMemberRest;
import com.example.demo.endpoint.rest.model.MemberRest;
import com.example.demo.model.CreateMember;
import com.example.demo.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MemberRestMapper {

    public CreateMember toDomainCreate(CreateMemberRest rest) {
        return new CreateMember(
                rest.getFirstName(),
                rest.getLastName(),
                rest.getBirthDate(),
                com.example.demo.model.Gender.valueOf(rest.getGender().name()),
                rest.getAddress(),
                rest.getProfession(),
                rest.getPhoneNumber(),
                rest.getEmail(),
                com.example.demo.model.MemberOccupation.valueOf(rest.getOccupation().name()),
                rest.getCollectivityIdentifier(),
                rest.getReferees(),
                rest.getRegistrationFeePaid(),
                rest.getMembershipDuesPaid()
        );
    }

    public MemberRest toRest(Member domain) {
        List<MemberRest> referees = new ArrayList<MemberRest>();

        if (domain.getReferees() != null) {
            for (Member referee : domain.getReferees()) {
                referees.add(toRestWithoutNestedReferees(referee));
            }
        }

        return new MemberRest(
                domain.getId(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getBirthDate(),
                com.example.demo.endpoint.rest.model.Gender.valueOf(domain.getGender().name()),
                domain.getAddress(),
                domain.getProfession(),
                domain.getPhoneNumber(),
                domain.getEmail(),
                com.example.demo.endpoint.rest.model.MemberOccupation.valueOf(domain.getOccupation().name()),
                referees
        );
    }

    public MemberRest toRestWithoutNestedReferees(Member domain) {
        return new MemberRest(
                domain.getId(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getBirthDate(),
                com.example.demo.endpoint.rest.model.Gender.valueOf(domain.getGender().name()),
                domain.getAddress(),
                domain.getProfession(),
                domain.getPhoneNumber(),
                domain.getEmail(),
                com.example.demo.endpoint.rest.model.MemberOccupation.valueOf(domain.getOccupation().name()),
                Collections.<MemberRest>emptyList()
        );
    }
}