package com.museum.admin.application.services;

import com.museum.admin.application.beans.SessionBean;
import com.museum.admin.application.beans.TourBean;
import com.museum.admin.application.beans.UserBean;
import com.museum.admin.application.beans.UserStatisticBean;
import com.museum.admin.application.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String FIND_ALL_QUERY = "select * from user";
    private static final String SET_APPROVAL = "update user set is_approved=? where id=?";
    private static final String UPDATE_PASSWORD = "update user set password=? where id=?";
    private static final String ACTIVE_USERS_QUERY = "select * from user inner join session s on user.username = s.username";

    public UserBean findById(int id) {
        return findAll().stream().filter(userBean -> userBean.getId() == id).findFirst().orElse(null);
    }

    public List<UserBean> findAll() {
        Connection connection = null;
        List<UserBean> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    UserBean userBean = new UserBean();
                    userBean.setId(resultSet.getInt("id"));
                    userBean.setUsername(resultSet.getString("username"));
                    userBean.setPassword(resultSet.getString("password"));
                    userBean.setName(resultSet.getString("name"));
                    userBean.setLastName(resultSet.getString("last_name"));
                    userBean.setEmail(resultSet.getString("e_mail"));
                    userBean.setApproved(resultSet.getBoolean("is_approved"));
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

    public boolean setApproval(int id, boolean status) {
        Connection connection = null;
        boolean returnStatus = false;
        if (findById(id) == null) {
            return false;
        }
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SET_APPROVAL)) {
                preparedStatement.setBoolean(1, status);
                preparedStatement.setInt(2, id);
                int ret = preparedStatement.executeUpdate();
                returnStatus = (ret == 1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return returnStatus;
    }

    public boolean updatePassword(int id, String password) {
        Connection connection = null;
        boolean returnStatus = false;
        if (findById(id) == null) {
            return false;
        }
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
                preparedStatement.setString(1, password);
                preparedStatement.setInt(2, id);
                int ret = preparedStatement.executeUpdate();
                returnStatus = (ret == 1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return returnStatus;
    }

    public List<SessionBean> getActiveUsers() {
        Connection connection = null;
        List<SessionBean> userList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(ACTIVE_USERS_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    SessionBean sessionBean = new SessionBean();
                    sessionBean.setId(resultSet.getInt("id"));
                    sessionBean.setUsername(resultSet.getString("username"));
                    sessionBean.setSessionKey(resultSet.getString("session_key"));
                    sessionBean.setValidUntil(resultSet.getTimestamp("valid_until"));
                    userList.add(sessionBean);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userList;
    }

    public int getNumberOfActiveUsers() {
        return getActiveUsers().size();
    }

    public int getTotalNumberOfUsers() {
        return findAll().size();
    }
}
