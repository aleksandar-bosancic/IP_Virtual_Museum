package com.museum.bank.admin.services;

import com.museum.bank.admin.beans.BankAccountBean;
import com.museum.bank.admin.beans.TransactionBean;
import com.museum.bank.admin.beans.Type;
import com.museum.bank.admin.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    private static final String FIND_ALL_QUERY = "select * from transaction";

    public List<TransactionBean> findAll() {
        Connection connection = null;
        List<TransactionBean> transactionList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    TransactionBean transaction = new TransactionBean();
                    transaction.setId(resultSet.getInt("id"));
                    transaction.setBankAccountId(resultSet.getInt("bank_account_id"));
                    transaction.setTransactionTime(resultSet.getTimestamp("transaction_time"));
                    transaction.setAmount(resultSet.getDouble("amount"));
                    transactionList.add(transaction);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return transactionList;
    }

    public List<TransactionBean> findAllByBankAccountId(int id) {
        return findAll().stream().filter(transaction -> transaction.getBankAccountId().equals(id)).collect(Collectors.toList());
    }
}
