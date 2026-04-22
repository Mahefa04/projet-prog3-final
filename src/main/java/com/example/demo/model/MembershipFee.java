package com.example.demo.model;

public class MembershipFee extends CreateMembershipFee {
    private String id;
    private ActivityStatus status;

    public MembershipFee(String id, ActivityStatus status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
}