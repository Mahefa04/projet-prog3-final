package com.example.demo.repository;

import com.example.demo.model.Collectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectivityRepositoryImpl implements CollectivityRepository {

    private final Connection connection;

    public CollectivityRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collectivity findById(String id) {
        String sql = "SELECT id, name, number, location FROM collectivities WHERE id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();

            Collectivity collectivity = null;

            if (rs.next()) {
                collectivity = mapResultSetToCollectivity(rs);
            }

            rs.close();
            pstmt.close();

            return collectivity;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity by id", e);
        }
    }

    @Override
    public Collectivity findByName(String name) {
        String sql = "SELECT id, name, number, location FROM collectivities WHERE name = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            Collectivity collectivity = null;

            if (rs.next()) {
                collectivity = mapResultSetToCollectivity(rs);
            }

            rs.close();
            pstmt.close();

            return collectivity;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity by name", e);
        }
    }

    @Override
    public Collectivity findByNumber(Integer number) {
        String sql = "SELECT id, name, number, location FROM collectivities WHERE number = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, number);

            ResultSet rs = pstmt.executeQuery();

            Collectivity collectivity = null;

            if (rs.next()) {
                collectivity = mapResultSetToCollectivity(rs);
            }

            rs.close();
            pstmt.close();

            return collectivity;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity by number", e);
        }
    }

    @Override
    public Collectivity save(Collectivity collectivity) {
        String sql = "UPDATE collectivities SET name = ?, number = ?, location = ? WHERE id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, collectivity.getName());

            if (collectivity.getNumber() != null) {
                pstmt.setInt(2, collectivity.getNumber());
            } else {
                pstmt.setNull(2, java.sql.Types.INTEGER);
            }

            pstmt.setString(3, collectivity.getLocation());
            pstmt.setString(4, collectivity.getId());

            pstmt.executeUpdate();
            pstmt.close();

            return collectivity;
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving collectivity", e);
        }
    }

    private Collectivity mapResultSetToCollectivity(ResultSet rs) throws SQLException {
        Collectivity collectivity = new Collectivity();

        collectivity.setId(rs.getString("id"));
        collectivity.setName(rs.getString("name"));

        int number = rs.getInt("number");
        if (rs.wasNull()) {
            collectivity.setNumber(null);
        } else {
            collectivity.setNumber(number);
        }

        collectivity.setLocation(rs.getString("location"));

        return collectivity;
    }
}