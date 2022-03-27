package com.museum.admin.application.services;

import com.museum.admin.application.beans.LogsBean;
import com.museum.admin.application.beans.MediaBean;
import com.museum.admin.application.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogsService {
    private static final String FIND_ALL_QUERY = "select * from logs";

    public List<LogsBean> findAll(){
        Connection connection = null;
        List<LogsBean> logsList = new ArrayList<>();
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    LogsBean logsBean = new LogsBean();
                    logsBean.setId(resultSet.getInt("id"));
                    logsBean.setUsername(resultSet.getString("username"));
                    logsBean.setCategory(resultSet.getString("category"));
                    logsBean.setLabel(resultSet.getString("label"));
                    logsBean.setTimestamp(resultSet.getTimestamp("timestamp"));
                    logsList.add(logsBean);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return logsList;
    }
}
