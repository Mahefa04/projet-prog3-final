package com.example.demo.endpoint.rest.mapper;

import com.example.demo.endpoint.rest.model.ActivityMemberAttendanceRest;
import com.example.demo.endpoint.rest.model.CreateActivityMemberAttendanceRest;
import com.example.demo.endpoint.rest.model.MemberDescriptionRest;
import com.example.demo.model.ActivityMemberAttendance;
import com.example.demo.model.CreateActivityMemberAttendance;
import com.example.demo.model.MemberDescription;
import org.springframework.stereotype.Component;

@Component
public class AttendanceRestMapper {

    public CreateActivityMemberAttendance toDomain(CreateActivityMemberAttendanceRest rest) {
        CreateActivityMemberAttendance domain = new CreateActivityMemberAttendance();

        domain.setMemberIdentifier(rest.getMemberIdentifier());
        domain.setAttendanceStatus(rest.getAttendanceStatus());
        domain.setAttendanceDate(rest.getAttendanceDate());

        return domain;
    }

    public ActivityMemberAttendanceRest toRest(ActivityMemberAttendance domain) {
        ActivityMemberAttendanceRest rest = new ActivityMemberAttendanceRest();

        rest.setId(domain.getId());
        rest.setAttendanceStatus(domain.getAttendanceStatus());
        rest.setMemberDescription(toMemberDescriptionRest(domain.getMemberDescription()));
        rest.setAttendanceDate(domain.getAttendanceDate());
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