package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class DivisionDao {
    private Connection conn;

    public DivisionDao(Connection conn) {
        this.conn = conn;
    }

    // get all data from table division
    public List<Division> getAll() {
        List<Division> division = new ArrayList<>();
        // List<Region> region = new ArrayList<>();
        // String query = "Select d.id, d.name, r.name from division d join region r on
        // d.regionId = r.id order by d.id asc";
        String query = "Select * from division d join region r  on d.regionId = r.id order by d.id asc";
        try {
            ResultSet resultSet = conn.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Division div = new Division();
                Region reg = new Region();
                div.setId(resultSet.getInt(1));
                div.setName(resultSet.getString(2));
                div.setRegion(reg);
                reg.setId(resultSet.getInt(4));
                reg.setName(resultSet.getString(5));
                division.add(div);
                // region.add(reg);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return division;
    }

    // get data by id from table division
    public Division getById(Integer Id) {
        Division divId = new Division();
        String que = "SELECT d.Id, d.Name, r.Name, r.Id FROM division d Join region r On d.regionId = r.Id WHERE d.Id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(que);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Region region = new Region();
                divId.setId(resultSet.getInt(1));
                divId.setName(resultSet.getString(2));
                divId.setRegion(region);
                region.setName(resultSet.getString(3));
                divId.setRegion(region);
                region.setId(resultSet.getInt(4));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return divId;
    }

    // public Division getRegion() {
    // Division division = new Division();
    // String query = "SELECT r.Name FROM division d Join region r On d.regionId =
    // r.Id";
    // try {
    // PreparedStatement prepareStatement = conn.prepareStatement(query);
    // ResultSet resultSet = prepareStatement.executeQuery();
    // while (resultSet.next()) {
    // Region region = new Region();
    // division.setRegion(region);
    // region.setName(resultSet.getString(1));
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return division;
    // }

    // insert data to table division
    public boolean insertData(Division division) {
        // division.setId(5);

        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("Insert INTO division(name, regionId) values(?,?)");
            preparedStatement.setString(1, division.getName());
            // Region region = new Region();
            // region.setId(5);
            // division.setRegion(region);
            preparedStatement.setInt(2, division.getRegion().getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    // update data on table division
    public boolean updateData(Division division) {
        try {
            String query = "Update division SET Name = ?, regionId = ? WHERE Id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            // preparedStatement.setInt(1, division.getId());
            preparedStatement.setString(1, division.getName());
            preparedStatement.setInt(2, division.getRegion().getId());
            preparedStatement.setInt(3, division.getId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteData(Integer id) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("delete from division where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

}
