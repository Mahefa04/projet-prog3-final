package com.example.demo.repository;

import com.example.demo.model.CollectivityStatistics;
import com.example.demo.model.MemberDescription;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<CollectivityStatistics> findStatisticsByCollectivity(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {
        List<CollectivityStatistics> result = new ArrayList<CollectivityStatistics>();

        String sql = """
                WITH active_fee AS (
                    SELECT
                        COALESCE(SUM(mf.amount), 0) AS expected_amount
                    FROM membership_fee mf
                    WHERE mf.collectivity_id = ?
                      AND mf.status = 'ACTIVE'
                      AND mf.eligible_from <= ?
                ),
                member_base AS (
                    SELECT
                        m.id AS member_id,
                        m.first_name AS first_name,
                        m.last_name AS last_name,
                        m.email AS email,
                        m.occupation AS occupation
                    FROM members m
                    WHERE m.collectivity_id = ?
                ),
                paid_by_member AS (
                    SELECT
                        mb.member_id,
                        COALESCE(SUM(
                            CASE
                                WHEN mp.creation_date BETWEEN ? AND ? THEN mp.amount
                                ELSE 0
                            END
                        ), 0) AS earned_amount
                    FROM member_base mb
                    LEFT JOIN member_payment mp
                        ON mp.member_id = mb.member_id
                    GROUP BY mb.member_id
                ),
                attendance_statistics AS (
                    SELECT
                        ama.member_id AS member_id,
                        COUNT(*) AS total_attendance_count,
                        COALESCE(SUM(
                            CASE
                                WHEN ama.attendance_status = 'ATTENDED' THEN 1
                                ELSE 0
                            END
                        ), 0) AS attended_count
                    FROM activity_member_attendance ama
                    JOIN collectivity_activity ca
                        ON ca.id = ama.activity_id
                    WHERE ca.collectivity_id = ?
                      AND ama.attendance_date BETWEEN ? AND ?
                    GROUP BY ama.member_id
                )
                SELECT
                    mb.member_id,
                    mb.first_name,
                    mb.last_name,
                    mb.email,
                    mb.occupation,
                    pbm.earned_amount,
                    GREATEST(active_fee.expected_amount - pbm.earned_amount, 0) AS unpaid_amount,
                    CASE
                        WHEN COALESCE(ast.total_attendance_count, 0) = 0 THEN 0
                        ELSE COALESCE(ast.attended_count, 0) * 100.0 / ast.total_attendance_count
                    END AS assiduity_percentage
                FROM member_base mb
                CROSS JOIN active_fee
                LEFT JOIN paid_by_member pbm
                    ON pbm.member_id = mb.member_id
                LEFT JOIN attendance_statistics ast
                    ON ast.member_id = mb.member_id
                ORDER BY mb.member_id
                """;

        try {
             PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, collectivityId);
            pstmt.setDate(2, Date.valueOf(to));
            pstmt.setString(3, collectivityId);

            pstmt.setDate(4, Date.valueOf(from));
            pstmt.setDate(5, Date.valueOf(to));

            pstmt.setString(6, collectivityId);
            pstmt.setDate(7, Date.valueOf(from));
            pstmt.setDate(8, Date.valueOf(to));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(mapResultSetToStatistics(rs));
            }

            rs.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity statistics", e);
        }
    }

    private CollectivityStatistics mapResultSetToStatistics(ResultSet rs) throws SQLException {
        MemberDescription memberDescription = new MemberDescription();

        memberDescription.setId(rs.getString("member_id"));
        memberDescription.setFirstName(rs.getString("first_name"));
        memberDescription.setLastName(rs.getString("last_name"));
        memberDescription.setEmail(rs.getString("email"));
        memberDescription.setOccupation(rs.getString("occupation"));

        CollectivityStatistics statistics = new CollectivityStatistics();

        statistics.setMemberDescription(memberDescription);
        statistics.setEarnedAmount(rs.getDouble("earned_amount"));
        statistics.setUnpaidAmount(rs.getDouble("unpaid_amount"));
        statistics.setAssiduityPercentage(rs.getDouble("assiduity_percentage"));

        return statistics;
    }
}