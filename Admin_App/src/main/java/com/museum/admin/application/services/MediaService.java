package com.museum.admin.application.services;

import com.museum.admin.application.beans.MediaBean;
import com.museum.admin.application.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MediaService {
    private static final String FIND_ALL_QUERY = "select * from media";
    private static final String DELETE_QUERY = "delete from media where id=?";
    private static final String INSERT_QUERY = "insert into media(id, museum_id, path, is_video) " +
            "value(?,?,?,?)";

    private List<MediaBean> mediaList;

    public MediaService(){
        mediaList = this.findAll();
    }

    private void updateMediaList(){
        mediaList = this.findAll();
    }

    private boolean checkIfMuseumHasVideo(int museumId){
        return mediaList.stream().filter(mediaBean -> mediaBean.getMuseumId() == museumId).anyMatch(MediaBean::isVideo);
    }

    public boolean insert(MediaBean mediaBean){
        Connection connection = null;
        boolean status = false;
        try {
            connection = ConnectionPool.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)){
                preparedStatement.setInt(1, 0);
                preparedStatement.setInt(2, mediaBean.getMuseumId());
                preparedStatement.setString(3, mediaBean.getPath());
                preparedStatement.setBoolean(4, mediaBean.isVideo());
                if(mediaBean.isVideo() && checkIfMuseumHasVideo(mediaBean.getMuseumId())){
                    return false;
                }
                int ret = preparedStatement.executeUpdate();
                status = (ret == 1);
                updateMediaList();
            }
        } catch (SQLException exception){
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return status;
    }

    public List<MediaBean> findAllByMuseumId(int id){
        return this.findAll().stream().filter(mediaBean -> mediaBean.getMuseumId() == id).collect(Collectors.toList());
    }

    public MediaBean findById(int id){
        return this.findAll().stream().filter(mediaBean -> mediaBean.getId() == id).findFirst().orElse(null);
    }

    public List<MediaBean> findAll(){
        Connection connection = null;
        List<MediaBean> mediaList = new ArrayList<>();
        try{
            connection = ConnectionPool.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    MediaBean mediaBean = new MediaBean();
                    mediaBean.setId(resultSet.getInt("id"));
                    mediaBean.setMuseumId(resultSet.getInt("museum_id"));
                    mediaBean.setPath(resultSet.getString("path"));
                    mediaBean.setVideo(resultSet.getBoolean("is_video"));
                    mediaList.add(mediaBean);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return mediaList;
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
                status = ret == 1;
                updateMediaList();
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return status;
    }
}
