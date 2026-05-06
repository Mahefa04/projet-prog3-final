package com.example.demo.service;

import com.example.demo.config.DataSourceConfig;
import com.example.demo.model.Activity;
import com.example.demo.model.Attendance;
import com.example.demo.model.Member;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ActivityRepository activityRepository;
    private final MemberRepository memberRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             ActivityRepository activityRepository,
                             MemberRepository memberRepository) {
        this.attendanceRepository = attendanceRepository;
        this.activityRepository = activityRepository;
        this.memberRepository = memberRepository;
    }

    public List<Attendance> createAttendances(String collectivityId, String activityId, List<Attendance> attendances) throws Exception {
        Activity activity = activityRepository.findById(activityId);
        if (activity == null) {
            throw new Exception("Activity not found: " + activityId);
        }

        if (!activity.getCollectivityId().equals(collectivityId)) {
            throw new Exception("Activity does not belong to collectivity: " + collectivityId);
        }

        List<Attendance> createdAttendances = new ArrayList<>();

        for (Attendance attendance : attendances) {
            Member member = memberRepository.findById(attendance.getMemberId());
            if (member == null) {
                throw new Exception("Member not found: " + attendance.getMemberId());
            }

            if (!member.getCollectivityId().equals(collectivityId)) {
                throw new Exception("Member does not belong to collectivity: " + collectivityId);
            }

            if (attendanceRepository.existsByActivityIdAndMemberId(activityId, attendance.getMemberId())) {
                throw new Exception("Attendance already recorded for member: " + attendance.getMemberId() + ". Cannot modify.");
            }

            if (attendance.getStatus() == null || 
                (!attendance.getStatus().equals("PRESENT") && 
                 !attendance.getStatus().equals("ABSENT") && 
                 !attendance.getStatus().equals("EXCUSED"))) {
                throw new Exception("Status must be PRESENT, ABSENT, or EXCUSED");
            }

            String attendanceId = "ATT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            attendance.setId(attendanceId);
            attendance.setActivityId(activityId);
            attendance.setCreatedAt(LocalDateTime.now());

            attendanceRepository.save(attendance);
            createdAttendances.add(attendance);
        }

        return createdAttendances;
    }

    public List<Attendance> getPresentMembers(String collectivityId, String activityId) throws Exception {
        Activity activity = activityRepository.findById(activityId);
        if (activity == null) {
            throw new Exception("Activity not found: " + activityId);
        }

        if (!activity.getCollectivityId().equals(collectivityId)) {
            throw new Exception("Activity does not belong to collectivity: " + collectivityId);
        }

        return attendanceRepository.findPresentByActivityId(activityId);
    }
}