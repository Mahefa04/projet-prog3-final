package com.example.demo.repository;

import com.example.demo.config.DataSourceConfig;
import com.example.demo.model.Attendance;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceRepository {

    private final DataSourceConfig dataSourceConfig;

    public AttendanceRepository(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public void save(Attendance attendance) throws SQLException {
        String sql = "INSERT INTO attendance (id, activity_id, member_id, status, excuse_reason, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dataSourceConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, attendance.getId());
            pstmt.setString(2, attendance.getActivityId());
            pstmt.setString(3, attendance.getMemberId());
            pstmt.setString(4, attendance.getStatus());
            pstmt.setString(5, attendance.getExcuseReason());
            pstmt.setTimestamp(6, Timestamp.valueOf(attendance.getCreatedAt()));
            
            pstmt.executeUpdate();
        }
    }

    public boolean existsByActivityIdAndMemberId(String activityId, String memberId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM attendance WHERE activity_id = ? AND member_id = ?";
        
        try (Connection conn = dataSourceConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, activityId);
            pstmt.setString(2, memberId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public List<Attendance> findPresentByActivityId(String activityId) throws SQLException {
        List<Attendance> attendances = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE activity_id = ? AND status = 'PRESENT'";
        
        try (Connection conn = dataSourceConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, activityId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setId(rs.getString("id"));
                attendance.setActivityId(rs.getString("activity_id"));
                attendance.setMemberId(rs.getString("member_id"));
                attendance.setStatus(rs.getString("status"));
                attendance.setExcuseReason(rs.getString("excuse_reason"));
                attendance.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                attendances.add(attendance);
            }
        }
        return attendances;
    }
}
