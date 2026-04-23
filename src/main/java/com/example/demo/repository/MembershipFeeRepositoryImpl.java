package com.example.demo.repository;

import com.example.demo.model.ActivityStatus;
import com.example.demo.model.Frequency;
import com.example.demo.model.MembershipFee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MembershipFeeRepositoryImpl implements MembershipFeeRepository {

    private final Connection connection;

    public MembershipFeeRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MembershipFee save(String collectivityId, MembershipFee fee) {
        try {
            String sql = "INSERT INTO membership_fee (id, collectivity_id, amount) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, fee.getId());
            ps.setString(2, collectivityId);
            ps.setDouble(3, fee.getAmount());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fee;
    }

    @Override
    public List<MembershipFee> findByCollectivityId(String collectivityId) {
        List<MembershipFee> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM membership_fee WHERE collectivity_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, collectivityId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MembershipFee fee = new MembershipFee();

                fee.setId(rs.getString("id"));
                fee.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                fee.setAmount(rs.getDouble("amount"));
                fee.setLabel(rs.getString("label"));

                // enum Frequency
                fee.setFrequency(Frequency.valueOf(rs.getString("frequency")));

                // enum ActivityStatus
                fee.setStatus(ActivityStatus.valueOf(rs.getString("status")));

                list.add(fee);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}