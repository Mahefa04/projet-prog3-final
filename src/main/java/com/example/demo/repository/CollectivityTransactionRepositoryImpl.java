package com.example.demo.repository;

import com.example.demo.model.CollectivityTransaction;
import com.example.demo.model.FinancialAccount;
import com.example.demo.model.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;

@Repository
public class CollectivityTransactionRepositoryImpl implements CollectivityTransactionRepository {

    private final DataSource dataSource;

    public CollectivityTransactionRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CollectivityTransaction> findByCollectivityIdAndTransactionDateBetween(
            String collectivityId,
            LocalDate from,
            LocalDate to) {

        List<CollectivityTransaction> transactions = new ArrayList<>();

        String sql = """
                SELECT id,
                       collectivity_id,
                       member_id,
                       financial_account_id,
                       amount,
                       reason,
                       transaction_date
                FROM collectivity_transaction
                WHERE collectivity_id = ?
                  AND transaction_date BETWEEN ? AND ?
                ORDER BY transaction_date
                """;

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, collectivityId);
            statement.setDate(2, Date.valueOf(from));
            statement.setDate(3, Date.valueOf(to));

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    CollectivityTransaction transaction = new CollectivityTransaction();

                    transaction.setId(rs.getString("id"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setReason(rs.getString("reason"));

                    Member member = new Member();
                    member.setId(rs.getString("member_id"));
                    transaction.setMemberDebited(member);

                    FinancialAccount account = new FinancialAccount();
                    account.setId(rs.getString("financial_account_id"));
                    transaction.setAccountCredited(account);

                    transactions.add(transaction);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}