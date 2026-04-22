package com.example.demo.repository;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.MemberPayment;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberPaymentRepository {

    public void save(MemberPayment payment) throws SQLException {
        String sql = "INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, account_credited_id, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, payment.getId());
            pstmt.setString(2, payment.getMemberId());
            pstmt.setString(3, payment.getMembershipFeeId());
            pstmt.setInt(4, payment.getAmount());
            pstmt.setString(5, payment.getPaymentMode());
            pstmt.setString(6, payment.getAccountCreditedId());
            pstmt.setDate(7, Date.valueOf(payment.getCreationDate()));

            pstmt.executeUpdate();
        }
    }

    public List<MemberPayment> findByMemberId(String memberId) throws SQLException {
        List<MemberPayment> payments = new ArrayList<>();
        String sql = "SELECT * FROM member_payment WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
        }
        return payments;
    }

    private MemberPayment mapResultSetToPayment(ResultSet rs) throws SQLException {
        MemberPayment payment = new MemberPayment();
        payment.setId(rs.getString("id"));
        payment.setMemberId(rs.getString("member_id"));
        payment.setMembershipFeeId(rs.getString("membership_fee_id"));
        payment.setAmount(rs.getInt("amount"));
        payment.setPaymentMode(rs.getString("payment_mode"));
        payment.setAccountCreditedId(rs.getString("account_credited_id"));
        payment.setCreationDate(rs.getDate("creation_date").toLocalDate());
        return payment;
    }
}
