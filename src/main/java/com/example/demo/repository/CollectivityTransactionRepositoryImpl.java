package com.example.demo.repository;

import com.example.demo.model.CollectivityTransaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityTransactionRepositoryImpl implements CollectivityTransactionRepository {

    private final Connection connection;

    public CollectivityTransactionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CollectivityTransaction> findByCollectivityIdAndTransactionDateBetween(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {
        List<CollectivityTransaction> transactions = new ArrayList<CollectivityTransaction>();

        String sql = """
                SELECT
                    mp.id AS member_payment_id,
                    m.collectivity_id AS collectivity_id,
                    mp.amount AS amount,
                    mp.payment_mode AS payment_mode,
                    mp.account_credited_id AS account_credited_id,
                    mp.creation_date AS creation_date
                FROM member_payment mp
                JOIN members m ON mp.member_id = m.id
                WHERE m.collectivity_id = ?
                  AND mp.creation_date BETWEEN ? AND ?
                ORDER BY mp.creation_date DESC
                """;

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

            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding collectivity transactions", e);
        }
    }

    private CollectivityTransaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        CollectivityTransaction transaction = new CollectivityTransaction();

        transaction.setId("trx-" + rs.getString("member_payment_id"));
        transaction.setCollectivityId(rs.getString("collectivity_id"));
        transaction.setMemberPaymentId(rs.getString("member_payment_id"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setPaymentMode(rs.getString("payment_mode"));
        transaction.setAccountCreditedId(rs.getString("account_credited_id"));

        Date creationDate = rs.getDate("creation_date");
        if (creationDate != null) {
            transaction.setCreationDate(creationDate.toLocalDate());
        }

        return transaction;
    }
}