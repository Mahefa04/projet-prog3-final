package com.example.demo.repository;

import com.example.demo.model.FinancialAccount;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancialAccountRepositoryImpl implements FinancialAccountRepository {

    private final Connection connection;

    public FinancialAccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FinancialAccount findById(String id) {
        String sql = "SELECT * FROM financial_account WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccount(rs);
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAmount(String id, Double newAmount) {
        String sql = "UPDATE financial_account SET amount = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, newAmount);
            pstmt.setString(2, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FinancialAccount> findByCollectivityId(String collectivityId) {
        String sql = "SELECT * FROM financial_account WHERE collectivity_id = ?";

        List<FinancialAccount> accounts = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, collectivityId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    accounts.add(mapResultSetToAccount(rs));
                }
            }

            return accounts;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FinancialAccount> findByCollectivityIdAtDate(String collectivityId, LocalDate at) {
        /*
         * Version simple :
         * Pour l’instant, on retourne les comptes de la collectivité.
         * Le paramètre "at" pourra être utilisé plus tard si tu ajoutes
         * une table de transactions ou une date dans la table.
         */
        return findByCollectivityId(collectivityId);
    }

    private FinancialAccount mapResultSetToAccount(ResultSet rs) throws SQLException {
        FinancialAccount account = new FinancialAccount();

        account.setId(rs.getString("id"));
        account.setCollectivityId(rs.getString("collectivity_id"));
        account.setAccountType(rs.getString("account_type"));
        account.setHolderName(rs.getString("holder_name"));
        account.setAmount(rs.getDouble("amount"));
        account.setMobileBankingService(rs.getString("mobile_banking_service"));
        account.setMobileNumber(rs.getString("mobile_number"));
        account.setBankName(rs.getString("bank_name"));
        account.setBankCode(rs.getInt("bank_code"));
        account.setBankBranchCode(rs.getInt("bank_branch_code"));
        account.setBankAccountNumber(rs.getInt("bank_account_number"));
        account.setBankAccountKey(rs.getInt("bank_account_key"));

        return account;
    }
}