
package com.example.demo.repository;

import com.example.demo.model.Statistics;
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
public class StatisticsRepository {

    private final DataSource dataSource;

    public StatisticsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Statistics> findGlobalStatistics(LocalDate from, LocalDate to) {
        List<Statistics> result = new ArrayList<Statistics>();

        String sql = """
                WITH active_fee_by_collectivity AS (
                    SELECT
                        c.id AS collectivity_id,
                        COALESCE(SUM(mf.amount), 0) AS expected_amount
                    FROM collectivities c
                    LEFT JOIN membership_fee mf
                        ON mf.collectivity_id = c.id
                       AND mf.status = 'ACTIVE'
                       AND mf.eligible_from <= ?
                    GROUP BY c.id
                ),
                paid_by_member AS (
                    SELECT
                        m.id AS member_id,
                        m.collectivity_id AS collectivity_id,
                        COALESCE(SUM(
                            CASE
                                WHEN mp.creation_date BETWEEN ? AND ? THEN mp.amount
                                ELSE 0
                            END
                        ), 0) AS paid_amount
                    FROM members m
                    LEFT JOIN member_payment mp
                        ON mp.member_id = m.id
                    GROUP BY m.id, m.collectivity_id
                ),
                member_status AS (
                    SELECT
                        pbm.member_id,
                        pbm.collectivity_id,
                        CASE
                            WHEN af.expected_amount > 0
                             AND pbm.paid_amount >= af.expected_amount
                            THEN 1
                            ELSE 0
                        END AS is_up_to_date
                    FROM paid_by_member pbm
                    JOIN active_fee_by_collectivity af
                        ON af.collectivity_id = pbm.collectivity_id
                )
                SELECT
                    c.id AS collectivity_id,
                    c.name AS collectivity_name,
                    COUNT(m.id) AS total_members,
                    COALESCE(SUM(ms.is_up_to_date), 0) AS up_to_date_members,
                    CASE
                        WHEN COUNT(m.id) = 0 THEN 0
                        ELSE COALESCE(SUM(ms.is_up_to_date), 0) * 100.0 / COUNT(m.id)
                    END AS up_to_date_percentage,
                    COUNT(
                        CASE
                            WHEN m.created_at BETWEEN ? AND ? THEN 1
                        END
                    ) AS new_members
                FROM collectivities c
                LEFT JOIN members m
                    ON m.collectivity_id = c.id
                LEFT JOIN member_status ms
                    ON ms.member_id = m.id
                GROUP BY c.id, c.name
                ORDER BY c.id
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(to));
            pstmt.setDate(2, Date.valueOf(from));
            pstmt.setDate(3, Date.valueOf(to));
            pstmt.setDate(4, Date.valueOf(from));
            pstmt.setDate(5, Date.valueOf(to));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(mapResultSetToStatistics(rs));
            }

            rs.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding global statistics", e);
        }
    }

    private Statistics mapResultSetToStatistics(ResultSet rs) throws SQLException {
        Statistics statistics = new Statistics();

        statistics.setCollectivityId(rs.getString("collectivity_id"));
        statistics.setCollectivityName(rs.getString("collectivity_name"));
        statistics.setTotalMembers(rs.getInt("total_members"));
        statistics.setUpToDateMembers(rs.getInt("up_to_date_members"));
        statistics.setUpToDatePercentage(rs.getDouble("up_to_date_percentage"));
        statistics.setNewMembers(rs.getInt("new_members"));

        return statistics;
    }
}