package com.example.demo.endpoint.rest.model;

public class ActivityMemberAttendanceRest {

    private String id;
    private MemberDescriptionRest memberDescription;
    private String attendanceStatus;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setMemberDescription(MemberDescriptionRest memberDescription) {
        this.memberDescription = memberDescription;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}