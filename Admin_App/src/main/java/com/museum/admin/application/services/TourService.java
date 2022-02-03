package com.museum.admin.application.services;

import com.museum.admin.application.beans.TourBean;
import com.museum.admin.application.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourService {
    private static final String FIND_ALL_QUERY = "select * from tour";
    private static final String DELETE_QUERY = "delete from tour where id=?";
    private static final String INSERT_QUERY = "insert into tour(id, museum_id, date, duration, price) " +
            "value(?,?,?,?, ?)";

    public boolean insert(TourBean tourBean){
        Connection connection = null;
        boolean status = false;
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)){
                preparedStatement.setInt(1, 0);
                preparedStatement.setInt(2, tourBean.getMuseumId());
                preparedStatement.setString(3, tourBean.getDate() + " " + tourBean.getTime());
                preparedStatement.setFloat(4, tourBean.getDuration());
                preparedStatement.setFloat(5, tourBean.getPrice());
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

    public TourBean findById(int id){
        List<TourBean> tourBeanList = this.findAll();
        for (TourBean bean : tourBeanList){
            if (bean.getId() == id){
                return bean;
            }
        }
        return null;
    }

    public List<TourBean> findAll(){
        Connection connection = null;
        List<TourBean> tourList = new ArrayList<>();
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    TourBean tourBean = new TourBean();
                    tourBean.setId(resultSet.getInt("id"));
                    tourBean.setMuseumId(resultSet.getInt("museum_id"));
                    tourBean.setDuration(resultSet.getFloat("duration"));
                    tourBean.setPrice(resultSet.getFloat("price"));
                    tourBean.setDate(String.valueOf(resultSet.getDate("date")));
                    tourBean.setTime(String.valueOf(resultSet.getTime("date")));
                    tourList.add(tourBean);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return tourList;
    }

    public boolean deleteById(int id){
        Connection connection = null;
        boolean status = false;
        if(this.findById(id) == null){
            return false;
        }
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
                preparedStatement.setInt(1, id);
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
