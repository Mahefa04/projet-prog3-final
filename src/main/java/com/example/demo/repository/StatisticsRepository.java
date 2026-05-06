package com.example.demo.repository;

import com.example.demo.config.DataSourceConfig;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

@Repository
public class StatisticsRepository {

    private final DataSourceConfig dataSourceConfig;

    public StatisticsRepository(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public List<Map<String, Object>> getAllCollectivities() {
        List<Map<String, Object>> collectivities = new ArrayList<>();
        String sql = "SELECT id, name FROM collectivities";
        try (Connection conn = dataSourceConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> c = new HashMap<>();
                c.put("id", rs.getString("id"));
                c.put("name", rs.getString("name"));
                collectivities.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collectivities;
    }

    public int getTotalMembersByCollectivity(String collectivityId) {
        String sql = "SELECT COUNT(*) FROM members WHERE collectivity_id = ?";
        try (Connection conn = dataSourceConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, collectivityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNewMembersCountByCollectivity(String collectivityId, LocalDate from, LocalDate to) {
        String sql = "SELECT COUNT(*) FROM members WHERE collectivity_id = ? AND joining_date BETWEEN ? AND ?";
        try (Connection conn = dataSourceConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, collectivityId);
            pstmt.setDate(2, Date.valueOf(from));
            pstmt.setDate(3, Date.valueOf(to));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMembersUpToDateCount(String collectivityId, LocalDate from, LocalDate to) {
        String sql = "SELECT COUNT(DISTINCT m.id) FROM member m " +
                     "JOIN member_payment mp ON m.id = mp.member_id " +
                     "JOIN membership_fee mf ON mp.membership_fee_id = mf.id " +
                     "WHERE m.collectivity_id = ? " +
                     "AND mf.status = 'ACTIVE' " +
                     "AND mp.creation_date BETWEEN ? AND ? " +
                     "AND mp.membership_fee_id IN (SELECT id FROM membership_fee WHERE collectivity_id = ? AND status = 'ACTIVE')";
        try (Connection conn = dataSourceConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, collectivityId);
            pstmt.setDate(2, Date.valueOf(from));
            pstmt.setDate(3, Date.valueOf(to));
            pstmt.setString(4, collectivityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
