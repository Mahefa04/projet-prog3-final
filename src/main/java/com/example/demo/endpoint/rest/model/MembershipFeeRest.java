package com.example.demo.endpoint.rest.model;

import com.example.demo.model.ActivityStatus;

public class MembershipFeeRest {
    private String id;
    private ActivityStatus status;

    public MembershipFeeRest(String id, ActivityStatus status) {
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

