package com.example.demo.service;

import com.example.demo.model.ActivityMemberAttendance;
import com.example.demo.model.CreateActivityMemberAttendance;
import com.example.demo.model.Member;
import com.example.demo.model.MemberDescription;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final MemberRepository memberRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            MemberRepository memberRepository
    ) {
        this.attendanceRepository = attendanceRepository;
        this.memberRepository = memberRepository;
    }

    public List<ActivityMemberAttendance> createAttendances(
            String collectivityId,
            String activityId,
            List<CreateActivityMemberAttendance> requests
    ) throws Exception {
        List<ActivityMemberAttendance> createdAttendances = new ArrayList<ActivityMemberAttendance>();

        for (int i = 0; i < requests.size(); i++) {
            CreateActivityMemberAttendance request = requests.get(i);

            if (request.getMemberIdentifier() == null) {
                throw new Exception("memberIdentifier is required");
            }

            Member member = memberRepository.findById(request.getMemberIdentifier());

            if (member == null) {
                throw new Exception("Member not found: " + request.getMemberIdentifier());
            }

            ActivityMemberAttendance attendance = new ActivityMemberAttendance();

            attendance.setId("ATT-" + activityId + "-" + request.getMemberIdentifier());
            attendance.setActivityId(activityId);
            attendance.setMemberId(request.getMemberIdentifier());
            attendance.setAttendanceStatus(request.getAttendanceStatus());

            MemberDescription description = new MemberDescription();
            description.setId(member.getId());
            description.setFirstName(member.getFirstName());
            description.setLastName(member.getLastName());
            description.setEmail(member.getEmail());


            description.setOccupation(String.valueOf(member.getOccupation()));

            attendance.setMemberDescription(description);

            ActivityMemberAttendance savedAttendance = attendanceRepository.save(attendance);

            createdAttendances.add(savedAttendance);
        }

        return createdAttendances;
    }

    public List<ActivityMemberAttendance> getAttendances(String collectivityId, String activityId) {
        return attendanceRepository.findByActivityId(activityId);
    }
}