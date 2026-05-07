package com.example.demo.repository;

import com.example.demo.model.ActivityMemberAttendance;
import com.example.demo.model.MemberDescription;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceRepositoryImpl implements AttendanceRepository {

    private Connection connection;

    public AttendanceRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ActivityMemberAttendance save(ActivityMemberAttendance attendance) {
        String sql = """
                INSERT INTO activity_member_attendance (
                    id,
                    activity_id,
                    member_id,
                    attendance_status,
                    attendance_date
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try {
             PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, attendance.getId());
            pstmt.setString(2, attendance.getActivityId());
            pstmt.setString(3, attendance.getMemberId());
            pstmt.setString(4, attendance.getAttendanceStatus());
            pstmt.setDate(5, Date.valueOf(attendance.getAttendanceDate()));

            pstmt.executeUpdate();

            return attendance;

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving attendance", e);
        }
    }

    @Override
    public List<ActivityMemberAttendance> findByActivityId(String activityId) {
        List<ActivityMemberAttendance> attendances = new ArrayList<ActivityMemberAttendance>();

        String sql = """
                SELECT
                    ama.id AS attendance_id,
                    ama.activity_id,
                    ama.member_id,
                    ama.attendance_status,
                    m.first_name,
                    m.last_name,
                    m.email,
                    m.occupation
                FROM activity_member_attendance ama
                JOIN members m ON m.id = ama.member_id
                WHERE ama.activity_id = ?
                ORDER BY ama.member_id
                """;

        try {
             PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, activityId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                attendances.add(mapResultSetToAttendance(rs));
            }

            rs.close();

            return attendances;

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding attendances", e);
        }
    }

    private ActivityMemberAttendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        ActivityMemberAttendance attendance = new ActivityMemberAttendance();

        attendance.setId(rs.getString("attendance_id"));
        attendance.setActivityId(rs.getString("activity_id"));
        attendance.setMemberId(rs.getString("member_id"));
        attendance.setAttendanceStatus(rs.getString("attendance_status"));

        MemberDescription memberDescription = new MemberDescription();
        memberDescription.setId(rs.getString("member_id"));
        memberDescription.setFirstName(rs.getString("first_name"));
        memberDescription.setLastName(rs.getString("last_name"));
        memberDescription.setEmail(rs.getString("email"));
        memberDescription.setOccupation(rs.getString("occupation"));

        attendance.setMemberDescription(memberDescription);

        return attendance;
    }
}