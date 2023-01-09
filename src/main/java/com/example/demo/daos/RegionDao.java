package com.example.demo.daos;

import com.example.demo.models.Region;
import java.sql.*;
import java.util.*;

public class RegionDao {
    private Connection conn;

    public RegionDao(Connection conn) {
        this.conn = conn;
    }

    public List<Region> getAllData() {
        List<Region> region = new ArrayList<>();
        String query = "Select id, name from region";
        try {
            ResultSet resultSet = conn.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Region reg = new Region();
                reg.setId(resultSet.getInt(1));
                reg.setName(resultSet.getString(2));
                region.add(reg);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return region;
    }

    // public Region getById(int regionId) {
    // Region regId = new Region();
    // String que = "select * from region where regionId = " + regionId;
    // try {
    // PreparedStatement preparedStatement = conn.prepareStatement(que);
    // ResultSet resultSet = conn.prepareStatement.executeQuery();
    // while (resultSet.next()) {

    // }

    public Region getById(Integer id) {
        Region regId = new Region();
        String que = "select * from region where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(que);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                regId.setId(resultSet.getInt(1));
                regId.setName(resultSet.getString(2));

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return regId;
    }

    // public boolean insertData(Region region) {
    // try {
    // PreparedStatement preparedStatement = conn
    // .prepareStatement("insert into region(regionId, regionName) values(?,?)");
    // preparedStatement.setInt(1, region.getRegionId());
    // preparedStatement.setString(2, region.getRegionName());
    // int temp = preparedStatement.executeUpdate();
    // return temp > 0;

    // } catch (SQLException e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // }
    // return false;
    // }
    public boolean insertData(Region region) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("Insert INTO region(name) values(?)");
            preparedStatement.setString(1, region.getName());
            // preparedStatement.executeUpdate();
            Integer temp = preparedStatement.executeUpdate();
            return temp > 0;
            // return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateData(Region region) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("update region set name = ? where id = ?");
            preparedStatement.setInt(2, region.getId());
            preparedStatement.setString(1, region.getName());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.getMessage();
        }
        return false;
    }

    public boolean deleteData(int id) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("delete from region where id = ?");
            preparedStatement.setInt(1, id);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

}
