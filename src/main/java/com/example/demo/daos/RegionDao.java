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
        String query = "Select regionId, regionName from region";
        try {
            ResultSet resultSet = conn.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Region reg = new Region();
                reg.setRegionId(resultSet.getInt(1));
                reg.setRegionName(resultSet.getString(2));
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

    public Region getById(Integer regionId) {
        Region regId = new Region();
        String que = "select * from region where regionId = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(que);
            preparedStatement.setInt(1, regionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                regId.setRegionId(resultSet.getInt(1));
                regId.setRegionName(resultSet.getString(2));

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
            PreparedStatement preparedStatement = conn.prepareStatement("Insert INTO region(regionName) values(?)");
            preparedStatement.setString(1, region.getRegionName());
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
                    .prepareStatement("update region set regionName = ? where regionId = ?");
            preparedStatement.setInt(2, region.getRegionId());
            preparedStatement.setString(1, region.getRegionName());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.getMessage();
        }
        return false;
    }

    public boolean deleteData(int regionId) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("delete from region where regionId = ?");
            preparedStatement.setInt(1, regionId);
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

}
