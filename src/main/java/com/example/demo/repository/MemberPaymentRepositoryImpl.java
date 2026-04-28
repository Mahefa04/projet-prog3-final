package com.example.demo.repository;

import com.example.demo.model.MemberPayment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberPaymentRepositoryImpl implements MemberPaymentRepository {

    private final Connection connection;

    public MemberPaymentRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public void save(MemberPayment payment) {
        String sql = """
                INSERT INTO member_payment
                (id, member_id, membership_fee_id, amount, payment_mode, account_credited_id, creation_date)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, payment.getId());
            pstmt.setString(2, payment.getMemberId());
            pstmt.setString(3, payment.getMembershipFeeId());
            pstmt.setInt(4, payment.getAmount());
            pstmt.setString(5, payment.getPaymentMode());
            pstmt.setString(6, payment.getAccountCreditedId());

            if (payment.getCreationDate() != null) {
                pstmt.setDate(7, Date.valueOf(payment.getCreationDate()));
            } else {
                pstmt.setNull(7, java.sql.Types.DATE);
            }

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving member payment", e);
        }
    }

    public List<MemberPayment> findByMemberId(String memberId) {
        List<MemberPayment> payments = new ArrayList<>();

        String sql = "SELECT * FROM member_payment WHERE member_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    payments.add(mapResultSetToPayment(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding payments by memberId", e);
        }

        return payments;
    }

    @Override
    public List<MemberPayment> saveAll(List<MemberPayment> payments) {
        for (MemberPayment payment : payments) {
            save(payment);
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

        Date date = rs.getDate("creation_date");
        if (date != null) {
            payment.setCreationDate(date.toLocalDate());
        }

        return payment;
    }
}