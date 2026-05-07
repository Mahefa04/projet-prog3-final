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

    private Connection connection;

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
                        m.id,
                        m.first_name,
                        m.last_name,
                        m.email,
                        m.occupation,
                        CASE
                            WHEN m.occupation = 'Président' THEN 'PRESIDENT'
                            WHEN m.occupation = 'Vice président' THEN 'VICE_PRESIDENT'
                            WHEN m.occupation = 'Secrétaire' THEN 'SECRETARY'
                            WHEN m.occupation = 'Trésorier' THEN 'TREASURER'
                            WHEN m.occupation = 'Confirmé' THEN 'SENIOR'
                            ELSE m.occupation
                        END AS normalized_occupation
                    FROM members m
                    WHERE m.collectivity_id = ?
                ),
                paid_by_member AS (
                    SELECT
                        mb.id AS member_id,
                        COALESCE(SUM(
                            CASE
                                WHEN mp.creation_date BETWEEN ? AND ? THEN mp.amount
                                ELSE 0
                            END
                        ), 0) AS earned_amount
                    FROM member_base mb
                    LEFT JOIN member_payment mp
                        ON mp.member_id = mb.id
                    GROUP BY mb.id
                ),
                required_activities AS (
                    SELECT
                        mb.id AS member_id,
                        COUNT(DISTINCT ca.id) AS required_count
                    FROM member_base mb
                    JOIN collectivity_activity ca
                        ON ca.collectivity_id = ?
                    JOIN collectivity_activity_occupation cao
                        ON cao.activity_id = ca.id
                       AND cao.occupation = mb.normalized_occupation
                    WHERE ca.executive_date BETWEEN ? AND ?
                       OR ca.executive_date IS NULL
                    GROUP BY mb.id
                ),
                attended_activities AS (
                    SELECT
                        mb.id AS member_id,
                        COUNT(DISTINCT ama.activity_id) AS attended_count
                    FROM member_base mb
                    JOIN activity_member_attendance ama
                        ON ama.member_id = mb.id
                       AND ama.attendance_status = 'ATTENDED'
                    JOIN collectivity_activity ca
                        ON ca.id = ama.activity_id
                       AND ca.collectivity_id = ?
                    JOIN collectivity_activity_occupation cao
                        ON cao.activity_id = ca.id
                       AND cao.occupation = mb.normalized_occupation
                    WHERE ca.executive_date BETWEEN ? AND ?
                       OR ca.executive_date IS NULL
                    GROUP BY mb.id
                )
                SELECT
                    mb.id AS member_id,
                    mb.first_name,
                    mb.last_name,
                    mb.email,
                    mb.occupation,
                    pbm.earned_amount,
                    GREATEST(active_fee.expected_amount - pbm.earned_amount, 0) AS unpaid_amount,
                    CASE
                        WHEN COALESCE(ra.required_count, 0) = 0 THEN 0
                        ELSE COALESCE(aa.attended_count, 0) * 100.0 / ra.required_count
                    END AS assiduity_percentage
                FROM member_base mb
                CROSS JOIN active_fee
                LEFT JOIN paid_by_member pbm
                    ON pbm.member_id = mb.id
                LEFT JOIN required_activities ra
                    ON ra.member_id = mb.id
                LEFT JOIN attended_activities aa
                    ON aa.member_id = mb.id
                ORDER BY mb.id
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

            pstmt.setString(9, collectivityId);
            pstmt.setDate(10, Date.valueOf(from));
            pstmt.setDate(11, Date.valueOf(to));

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