package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.MemberRest;
import com.example.demo.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberRestMapper {

    public MemberRest toRest(Member member) {
        MemberRest rest = new MemberRest();

        rest.setId(member.getId());
        rest.setFirstName(member.getFirstName());
        rest.setLastName(member.getLastName());
        rest.setBirthDate(member.getBirthDate());
        rest.setGender(member.getGender());
        rest.setAddress(member.getAddress());
        rest.setProfession(member.getProfession());
        rest.setPhone(member.getPhone());
        rest.setEmail(member.getEmail());
        Member.setOccupation(11, member.getOccupation("occupation"));

        return rest;
    }
}