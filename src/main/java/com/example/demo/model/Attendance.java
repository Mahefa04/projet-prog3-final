package com.example.demo.model;

import java.time.LocalDateTime;

public class Attendance {
    private String id;
    private String activityId;
    private String memberId;
    private String status;
    private String excuseReason;
    private LocalDateTime createdAt;

    public Attendance()
    {
    }

    public Attendance(String id, String activityId, String memberId, String status, String excuseReason, LocalDateTime createdAt)
    {
        this.id = id;
        this.activityId = activityId;
        this.memberId = memberId;
        this.status = status;
        this.excuseReason = excuseReason;
        this.createdAt = createdAt;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getActivityId()
    {
        return activityId;
    }

    public void setActivityId(String activityId)
    {
        this.activityId = activityId;
    }

    public String getMemberId()
    {
        return memberId;
    }

    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getExcuseReason()
    {
        return excuseReason;
    }

    public void setExcuseReason(String excuseReason)
    {
        this.excuseReason = excuseReason;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }
}