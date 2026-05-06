package com.example.demo.repository;

import com.example.demo.model.CollectivityStatistics;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CollectivityStatisticsRepositoryImpl implements CollectivityStatisticsRepository {

    private final Connection connection;
    public CollectivityStatisticsRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean collectivityExists(String collectivityId) {
        String sql = "SELECT COUNT(*) FROM collectivities WHERE id = ?";

        try {
             PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, collectivityId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

            rs.close();

            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Error while checking collectivity existence", e);
        }
    }

    @Override
    public List<CollectivityStatistics> findLocalStatistics(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {
        List<CollectivityStatistics> statistics = new ArrayList<CollectivityStatistics>();

        String sql = """
                WITH expected_fee AS (
                    SELECT COALESCE(SUM(mf.amount), 0) AS expected_amount
                    FROM membership_fee mf
                    WHERE mf.collectivity_id = ?
                      AND mf.status = 'ACTIVE'
                      AND mf.eligible_from <= ?
                ),
                paid_by_member AS (
                    SELECT
                        m.id AS member_id,
                        m.first_name AS first_name,
                        m.last_name AS last_name,
                        COALESCE(SUM(mp.amount), 0) AS earned_amount
                    FROM members m
                    LEFT JOIN member_payment mp
                        ON mp.member_id = m.id
                       AND mp.creation_date BETWEEN ? AND ?
                    WHERE m.collectivity_id = ?
                    GROUP BY m.id, m.first_name, m.last_name
                )
                SELECT
                    pbm.member_id,
                    pbm.first_name,
                    pbm.last_name,
                    pbm.earned_amount,
                    CASE
                        WHEN expected_fee.expected_amount - pbm.earned_amount < 0 THEN 0
                        ELSE expected_fee.expected_amount - pbm.earned_amount
                    END AS potential_unpaid_amount
                FROM paid_by_member pbm
                CROSS JOIN expected_fee
                ORDER BY pbm.member_id
                """;

        try {
             PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, collectivityId);
            pstmt.setDate(2, Date.valueOf(to));
            pstmt.setDate(3, Date.valueOf(from));
            pstmt.setDate(4, Date.valueOf(to));
            pstmt.setString(5, collectivityId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                statistics.add(mapResultSetToStatistics(rs));
            }

            rs.close();

            return statistics;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity local statistics", e);
        }
    }

    private CollectivityStatistics mapResultSetToStatistics(ResultSet rs) throws SQLException {
        CollectivityStatistics statistics = new CollectivityStatistics();

        statistics.setMemberId(rs.getString("member_id"));
        statistics.setFirstName(rs.getString("first_name"));
        statistics.setLastName(rs.getString("last_name"));
        statistics.setEarnedAmount(rs.getDouble("earned_amount"));
        statistics.setPotentialUnpaidAmount(rs.getDouble("potential_unpaid_amount"));

        return statistics;
    }
}