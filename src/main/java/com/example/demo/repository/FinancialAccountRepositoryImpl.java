package com.example.demo.repository;

import com.example.demo.model.FinancialAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinancialAccountRepositoryImpl {

    private final Connection connection;

    public FinancialAccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public FinancialAccount findById(String id) throws SQLException {
        String sql = "SELECT * FROM financial_account WHERE id = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();

        FinancialAccount account = null;

        if (rs.next()) {
            account = mapResultSetToAccount(rs);
        }

        rs.close();
        pstmt.close();

        return account;
    }

    public void updateAmount(String id, Double newAmount) throws SQLException {
        String sql = "UPDATE financial_account SET amount = ? WHERE id = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setDouble(1, newAmount);
        pstmt.setString(2, id);

        pstmt.executeUpdate();

        pstmt.close();
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