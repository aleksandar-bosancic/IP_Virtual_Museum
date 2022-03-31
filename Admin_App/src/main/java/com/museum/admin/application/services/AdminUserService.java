package com.museum.admin.application.services;

import com.museum.admin.application.beans.AdminUserBean;
import com.museum.admin.application.beans.UserBean;
import com.museum.admin.application.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminUserService {
    private static final String FIND_ALL_QUERY = "select * from admin_user";
    private static final String UPDATE_TOKEN = "update admin_user set token=? where id=?";

    public AdminUserBean findById(int id) {
        return findAll().stream().filter(admin -> admin.getId() == id).findFirst().orElse(null);
    }

    public List<AdminUserBean> findAll() {
        Connection connection = null;
        List<AdminUserBean> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    AdminUserBean userBean = new AdminUserBean();
                    userBean.setId(resultSet.getInt("id"));
                    userBean.setUsername(resultSet.getString("username"));
                    userBean.setPassword(resultSet.getString("password"));
                    userBean.setToken(resultSet.getString("token"));
                    userList.add(userBean);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return userList;
    }

    public boolean updateToken(String token, int id) {
        Connection connection = null;
        boolean status = false;
        if(this.findById(id) == null){
            return status;
        }
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOKEN)) {
                preparedStatement.setString(1, token);
                preparedStatement.setInt(2, id);
                int ret = preparedStatement.executeUpdate();
                status = (ret == 1);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return status;
    }
}
