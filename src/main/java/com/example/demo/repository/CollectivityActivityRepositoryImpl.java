package com.example.demo.repository;

import com.example.demo.model.CollectivityActivity;
import com.example.demo.model.MonthlyRecurrenceRule;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityActivityRepositoryImpl implements CollectivityActivityRepository {

    private final DataSource dataSource;

    public CollectivityActivityRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean collectivityExists(String collectivityId) {
        String sql = "SELECT COUNT(*) FROM collectivities WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

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
    public CollectivityActivity save(String collectivityId, CollectivityActivity activity) {
        String sql = """
                INSERT INTO collectivity_activity (
                    id,
                    collectivity_id,
                    label,
                    activity_type,
                    executive_date,
                    week_ordinal,
                    day_of_week
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, activity.getId());
            pstmt.setString(2, collectivityId);
            pstmt.setString(3, activity.getLabel());
            pstmt.setString(4, activity.getActivityType());

            if (activity.getExecutiveDate() != null) {
                pstmt.setDate(5, Date.valueOf(activity.getExecutiveDate()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }

            if (activity.getRecurrenceRule() != null) {
                pstmt.setInt(6, activity.getRecurrenceRule().getWeekOrdinal());
                pstmt.setString(7, activity.getRecurrenceRule().getDayOfWeek());
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
                pstmt.setNull(7, java.sql.Types.VARCHAR);
            }

            pstmt.executeUpdate();

            saveOccupations(connection, activity);

            return activity;

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving collectivity activity", e);
        }
    }

    @Override
    public List<CollectivityActivity> findByCollectivityId(String collectivityId) {
        List<CollectivityActivity> activities = new ArrayList<CollectivityActivity>();

        String sql = """
                SELECT
                    id,
                    collectivity_id,
                    label,
                    activity_type,
                    executive_date,
                    week_ordinal,
                    day_of_week
                FROM collectivity_activity
                WHERE collectivity_id = ?
                ORDER BY id
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, collectivityId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CollectivityActivity activity = mapResultSetToActivity(rs);
                activity.setMemberOccupationConcerned(findOccupationsByActivityId(connection, activity.getId()));
                activities.add(activity);
            }

            rs.close();

            return activities;

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity activities", e);
        }
    }

    private void saveOccupations(Connection connection, CollectivityActivity activity) throws SQLException {
        if (activity.getMemberOccupationConcerned() == null) {
            return;
        }

        String sql = """
                INSERT INTO collectivity_activity_occupation (
                    activity_id,
                    occupation
                )
                VALUES (?, ?)
                """;

        PreparedStatement pstmt = connection.prepareStatement(sql);

        int i;
        for (i = 0; i < activity.getMemberOccupationConcerned().size(); i++) {
            pstmt.setString(1, activity.getId());
            pstmt.setString(2, activity.getMemberOccupationConcerned().get(i));
            pstmt.executeUpdate();
        }

        pstmt.close();
    }

    private List<String> findOccupationsByActivityId(Connection connection, String activityId) throws SQLException {
        List<String> occupations = new ArrayList<String>();

        String sql = """
                SELECT occupation
                FROM collectivity_activity_occupation
                WHERE activity_id = ?
                ORDER BY occupation
                """;

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, activityId);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            occupations.add(rs.getString("occupation"));
        }

        rs.close();
        pstmt.close();

        return occupations;
    }

    private CollectivityActivity mapResultSetToActivity(ResultSet rs) throws SQLException {
        CollectivityActivity activity = new CollectivityActivity();

        activity.setId(rs.getString("id"));
        activity.setCollectivityId(rs.getString("collectivity_id"));
        activity.setLabel(rs.getString("label"));
        activity.setActivityType(rs.getString("activity_type"));

        Date executiveDate = rs.getDate("executive_date");
        if (executiveDate != null) {
            activity.setExecutiveDate(executiveDate.toLocalDate());
        }

        int weekOrdinal = rs.getInt("week_ordinal");
        if (!rs.wasNull()) {
            MonthlyRecurrenceRule rule = new MonthlyRecurrenceRule();
            rule.setWeekOrdinal(weekOrdinal);
            rule.setDayOfWeek(rs.getString("day_of_week"));
            activity.setRecurrenceRule(rule);
        }

        return activity;
    }
}