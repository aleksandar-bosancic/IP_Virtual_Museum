package com.museum.bank.admin.services;

import com.museum.bank.admin.beans.BankAccountBean;
import com.museum.bank.admin.beans.Type;
import com.museum.bank.admin.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountService {
    private static final String FIND_ALL_QUERY = "select * from bank_account";
    private static final String SET_BLOCKED_QUERY = "update bank_account set blocked=? where id=?";

    public List<BankAccountBean> findAll() {
        Connection connection = null;
        List<BankAccountBean> bankAccountList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    BankAccountBean account = new BankAccountBean();
                    account.setId(resultSet.getInt("id"));
                    account.setHolderName(resultSet.getString("holder_name"));
                    account.setNumber(resultSet.getString("number"));
                    account.setType(Type.valueOf(resultSet.getString("type")));
                    account.setValidThru(resultSet.getTimestamp("valid_thru"));
                    account.setPin(resultSet.getString("pin"));
                    account.setBalance(resultSet.getDouble("balance"));
                    account.setBlocked(resultSet.getBoolean("blocked"));
                    bankAccountList.add(account);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return bankAccountList;
    }

    public BankAccountBean login(BankAccountBean account) {
        return findAll().stream().filter(bean -> bean.getNumber().equals(account.getNumber())
                && bean.getHolderName().equals(account.getHolderName())
                && bean.getPin().equals(account.getPin())).findAny().orElse(null);
    }

    public boolean setBlocked(int id, boolean status) {
        Connection connection = null;
        boolean returnStatus = false;
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SET_BLOCKED_QUERY)) {
                preparedStatement.setBoolean(1, status);
                preparedStatement.setInt(2, id);
                int ret = preparedStatement.executeUpdate();
                returnStatus = (ret == 1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return returnStatus;
    }
}
