package com.example.demo.endpoint.rest.model;

import java.time.LocalDate;

public class ActivityMemberAttendanceRest {

    private String id;
    private MemberDescriptionRest memberDescription;
    private String attendanceStatus;
    private LocalDate attendanceDate;

    public ActivityMemberAttendanceRest() {
    }

    public String getId() {
        return id;
    }

    public MemberDescriptionRest getMemberDescription() {
        return memberDescription;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMemberDescription(MemberDescriptionRest memberDescription) {
        this.memberDescription = memberDescription;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}