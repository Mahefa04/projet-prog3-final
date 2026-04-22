package com.example.demo.repository;


import com.example.demo.database.DatabaseConnection;
import com.example.demo.model.CollectivityTransaction;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CollectivityTransactionRepository {

    public void save(CollectivityTransaction transaction) throws SQLException {
        String sql = "INSERT INTO collectivity_transaction (id, collectivity_id, member_payment_id, amount, payment_mode, account_credited_id, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transaction.getId());
            pstmt.setString(2, transaction.getCollectivityId());
            pstmt.setString(3, transaction.getMemberPaymentId());
            pstmt.setDouble(4, transaction.getAmount());
            pstmt.setString(5, transaction.getPaymentMode());
            pstmt.setString(6, transaction.getAccountCreditedId());
            pstmt.setDate(7, Date.valueOf(transaction.getCreationDate()));

            pstmt.executeUpdate();
        }
    }

    public List<CollectivityTransaction> findByCollectivityIdAndDateRange(String collectivityId, LocalDate from, LocalDate to) throws SQLException {
        List<CollectivityTransaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM collectivity_transaction WHERE collectivity_id = ? AND creation_date BETWEEN ? AND ? ORDER BY creation_date DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, collectivityId);
            pstmt.setDate(2, Date.valueOf(from));
            pstmt.setDate(3, Date.valueOf(to));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                transactions.add(mapResultSetToTransaction(rs));
            }
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
        transaction.setCreationDate(rs.getDate("creation_date").toLocalDate());
        return transaction;
    }
}
