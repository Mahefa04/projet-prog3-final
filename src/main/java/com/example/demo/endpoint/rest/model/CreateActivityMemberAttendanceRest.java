package com.example.demo.endpoint.rest.model;

import java.time.LocalDate;

public class CreateActivityMemberAttendanceRest {

    private String memberIdentifier;
    private String attendanceStatus;
    private LocalDate attendanceDate;

    public CreateActivityMemberAttendanceRest() {
    }

    public String getMemberIdentifier() {
        return memberIdentifier;
    }

    public void setMemberIdentifier(String memberIdentifier) {
        this.memberIdentifier = memberIdentifier;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}