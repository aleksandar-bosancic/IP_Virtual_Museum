package com.museum.admin.application.services;

import com.museum.admin.application.beans.UserBean;
import com.museum.admin.application.beans.UserStatisticBean;
import com.museum.admin.application.connection.ConnectionPool;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserStatisticService {
    private static final String FIND_ALL_QUERY = "select * from user_statistic";

    public List<UserStatisticBean> findAll() {
        Connection connection = null;
        List<UserStatisticBean> userStatisticList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    UserStatisticBean userBean = new UserStatisticBean();
                    userBean.setId(resultSet.getInt("id"));
                    userBean.setTimeActive(resultSet.getTimestamp("time_active"));
                    userStatisticList.add(userBean);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return userStatisticList;
    }

    public List<UserStatisticBean> findAllLastDay() {
        List<UserStatisticBean> statisticList = new ArrayList<>();
        Timestamp current = Timestamp.from(Instant.now());
        Timestamp dayBefore = Timestamp.from(Instant.now().minus(Duration.ofHours(24)));
        for (UserStatisticBean userStatistic : findAll()) {
            if (userStatistic.getTimeActive().before(current) && userStatistic.getTimeActive().after(dayBefore)){
                statisticList.add(userStatistic);
            }
        }
        return statisticList;
    }
}
