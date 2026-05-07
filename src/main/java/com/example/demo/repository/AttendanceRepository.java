package com.example.demo.repository;

import com.example.demo.model.ActivityMemberAttendance;

import java.util.List;

public interface AttendanceRepository {

    ActivityMemberAttendance save(ActivityMemberAttendance attendance);

    List<ActivityMemberAttendance> findByActivityId(String activityId);
}