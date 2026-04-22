package com.example.demo.service.factory;

import com.example.demo.model.Collectivity;
import com.example.demo.model.CreateMember;
import com.example.demo.model.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberFactory {

    public Member create(CreateMember input, Collectivity collectivity, List<Member> referees) {
        return new Member(
                null,
                input.getFirstName(),
                input.getLastName(),
                input.getBirthDate(),
                input.getGender(),
                input.getAddress(),
                input.getProfession(),
                input.getPhoneNumber(),
                input.getEmail(),
                input.getOccupation(),
                collectivity,
                referees
        );
    }
}