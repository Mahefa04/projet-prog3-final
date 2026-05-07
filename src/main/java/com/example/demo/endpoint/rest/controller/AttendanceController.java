package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.mapper.AttendanceRestMapper;
import com.example.demo.endpoint.rest.model.ActivityMemberAttendanceRest;
import com.example.demo.endpoint.rest.model.CreateActivityMemberAttendanceRest;
import com.example.demo.model.ActivityMemberAttendance;
import com.example.demo.model.CreateActivityMemberAttendance;
import com.example.demo.service.AttendanceService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AttendanceRestMapper attendanceRestMapper;

    public AttendanceController(
            AttendanceService attendanceService,
            AttendanceRestMapper attendanceRestMapper
    ) {
        this.attendanceService = attendanceService;
        this.attendanceRestMapper = attendanceRestMapper;
    }

    @PostMapping("/collectivities/{id}/activities/{activityId}/attendance")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ActivityMemberAttendanceRest> createAttendances(
            @PathVariable String id,
            @PathVariable String activityId,
            @RequestBody List<CreateActivityMemberAttendanceRest> request
    ) throws Exception {
        List<CreateActivityMemberAttendance> attendances = new ArrayList<CreateActivityMemberAttendance>();
        List<ActivityMemberAttendance> createdAttendances;
        List<ActivityMemberAttendanceRest> response = new ArrayList<ActivityMemberAttendanceRest>();

        for (int i = 0; i < request.size(); i++) {
            attendances.add(attendanceRestMapper.toDomain(request.get(i)));
        }

        createdAttendances = attendanceService.createAttendances(id, activityId, attendances);

        for (int i = 0; i < createdAttendances.size(); i++) {
            response.add(attendanceRestMapper.toRest(createdAttendances.get(i)));
        }

        return response;
    }
}