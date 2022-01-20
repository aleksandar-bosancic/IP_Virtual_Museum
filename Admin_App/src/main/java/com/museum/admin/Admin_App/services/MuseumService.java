package com.museum.admin.Admin_App.services;

import com.museum.admin.Admin_App.beans.MuseumBean;
import com.museum.admin.Admin_App.connection.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MuseumService {
    private static final String INSERT_QUERY = "insert into museum" +
            "(id, name, address, phone_number, country, city, type, latitude, longitude) " +
            "value (?,?,?,?,?,?,?,?,?)";
    private static final String FIND_ALL_QUERY = "select * from museum";
    private static final String UPDATE_QUERY = "update museum set name=?, address=?, phone_number=?, country=?, " +
            "city=?, type=?, latitude=?, longitude=? where id=?";

    public boolean insert(MuseumBean museumBean){
        Connection connection = null;
        boolean status = false;
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, museumBean.getName());
                preparedStatement.setString(3, museumBean.getAddress());
                preparedStatement.setString(4, museumBean.getPhoneNumber());
                preparedStatement.setString(5, museumBean.getCountry());
                preparedStatement.setString(6, museumBean.getCity());
                preparedStatement.setString(7, museumBean.getType());
                preparedStatement.setFloat(8, museumBean.getLatitude());
                preparedStatement.setFloat(9, museumBean.getLongitude());
                status = preparedStatement.execute();
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return status;
    }

    public MuseumBean findById(int id){
        List<MuseumBean> museumList = this.findAll();
        for (MuseumBean museum : museumList){
            if(museum.getId() == id){
                return museum;
            }
        }
        return null;
    }

    public List<MuseumBean> findAll(){
        Connection connection = null;
        List<MuseumBean> allMuseums = new ArrayList<>();
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    MuseumBean museumBean = new MuseumBean();
                    museumBean.setId(resultSet.getInt("id"));
                    museumBean.setName(resultSet.getString("name"));
                    museumBean.setAddress(resultSet.getString("address"));
                    museumBean.setPhoneNumber(resultSet.getString("phone_number"));
                    museumBean.setCountry(resultSet.getString("country"));
                    museumBean.setCity(resultSet.getString("city"));
                    museumBean.setType(resultSet.getString("type"));
                    museumBean.setLatitude(resultSet.getFloat("latitude"));
                    museumBean.setLongitude(resultSet.getFloat("longitude"));
                    allMuseums.add(museumBean);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return allMuseums;
    }

    public boolean updateById(int id, MuseumBean updatedMuseum){
        Connection connection = null;
        boolean status = false;
        if(this.findById(id) == null){
            return false;
        }
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
                preparedStatement.setString(1, updatedMuseum.getName());
                preparedStatement.setString(2, updatedMuseum.getAddress());
                preparedStatement.setString(3, updatedMuseum.getPhoneNumber());
                preparedStatement.setString(4, updatedMuseum.getCountry());
                preparedStatement.setString(5, updatedMuseum.getCity());
                preparedStatement.setString(6, updatedMuseum.getType());
                preparedStatement.setFloat(7, updatedMuseum.getLatitude());
                preparedStatement.setFloat(8, updatedMuseum.getLongitude());
                preparedStatement.setInt(9, updatedMuseum.getId());
                status = preparedStatement.execute();
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return status;
    }
}
