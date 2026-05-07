package com.example.demo.endpoint.rest.model;

public class CreateActivityMemberAttendanceRest {

    private String memberIdentifier;
    private String attendanceStatus;

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

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}