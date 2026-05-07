package com.example.demo.model;

public class ActivityMemberAttendance {

    private String id;
    private String activityId;
    private String memberId;
    private MemberDescription memberDescription;
    private String attendanceStatus;

    public ActivityMemberAttendance() {
    }

    public ActivityMemberAttendance(
            String id,
            String activityId,
            String memberId,
            MemberDescription memberDescription,
            String attendanceStatus
    ) {
        this.id = id;
        this.activityId = activityId;
        this.memberId = memberId;
        this.memberDescription = memberDescription;
        this.attendanceStatus = attendanceStatus;
    }

    public String getId() {
        return id;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getMemberId() {
        return memberId;
    }

    public MemberDescription getMemberDescription() {
        return memberDescription;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberDescription(MemberDescription memberDescription) {
        this.memberDescription = memberDescription;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}