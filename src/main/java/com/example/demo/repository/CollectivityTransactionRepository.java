package com.example.demo.repository;

import com.example.demo.model.CollectivityTransaction;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CollectivityTransactionRepository {

    private final Connection connection;

    public CollectivityTransactionRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(CollectivityTransaction transaction) {
        String sql = "INSERT INTO collectivity_transaction (id, collectivity_id, member_payment_id, amount, payment_mode, account_credited_id, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, transaction.getId());
            pstmt.setString(2, transaction.getCollectivityId());
            pstmt.setString(3, transaction.getMemberPaymentId());
            pstmt.setDouble(4, transaction.getAmount());
            pstmt.setString(5, transaction.getPaymentMode());
            pstmt.setString(6, transaction.getAccountCreditedId());

            if (transaction.getCreationDate() != null) {
                pstmt.setDate(7, Date.valueOf(transaction.getCreationDate()));
            } else {
                pstmt.setNull(7, java.sql.Types.DATE);
            }

            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving collectivity transaction", e);
        }
    }

    public List<CollectivityTransaction> findByCollectivityIdAndDateRange(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {
        List<CollectivityTransaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM collectivity_transaction WHERE collectivity_id = ? AND creation_date BETWEEN ? AND ? ORDER BY creation_date DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, collectivityId);
            pstmt.setDate(2, Date.valueOf(from));
            pstmt.setDate(3, Date.valueOf(to));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }

            rs.close();
            pstmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding transactions", e);
        }

        return transactions;
    }

    private CollectivityTransaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        CollectivityTransaction transaction = new CollectivityTransaction();

        transaction.setId(rs.getString("id"));
        transaction.setCollectivityId(rs.getString("collectivity_id"));
        transaction.setMemberPaymentId(rs.getString("member_payment_id"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setPaymentMode(rs.getString("payment_mode"));
        transaction.setAccountCreditedId(rs.getString("account_credited_id"));

        Date date = rs.getDate("creation_date");
        if (date != null) {
            transaction.setCreationDate(date.toLocalDate());
        }

        return transaction;
    }
}