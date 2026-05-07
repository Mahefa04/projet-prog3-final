package com.example.demo.model;

import java.time.LocalDate;

public class CreateActivityMemberAttendance {

    private String memberIdentifier;
    private String attendanceStatus;
    private LocalDate attendanceDate;

    public CreateActivityMemberAttendance() {
    }

    public String getMemberIdentifier() {
        return memberIdentifier;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
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