package com.example.demo.endpoint.rest.controller;

import com.example.demo.model.Attendance;
import com.example.demo.service.AttendanceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/collectivities")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/{id}/activities/{activityId}/attendance")
    public List<Attendance> createAttendances(
            @PathVariable String id,
            @PathVariable String activityId,
            @RequestBody List<Attendance> attendances) throws Exception {
        
        return attendanceService.createAttendances(id, activityId, attendances);
    }

    @GetMapping("/{id}/activities/{activityId}/attendance/present")
    public List<Attendance> getPresentMembers(
            @PathVariable String id,
            @PathVariable String activityId) throws Exception {
        
        return attendanceService.getPresentMembers(id, activityId);
    }
}