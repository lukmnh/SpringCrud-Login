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
        String que = "select * from division where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(que);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                divId.setId(resultSet.getInt(1));
                divId.setName(resultSet.getString(2));
                // divId.setRegion(resultSet.getObject(3));

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return divId;
    }

    // insert data to table division
    public boolean insertData(Division division) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("insert into division(id, name, regionId) values(?,?,?)");
            preparedStatement.setInt(1, division.getId());
            preparedStatement.setString(2, division.getName());
            // preparedStatement.setString(3, division.getRegionId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    // update data on table division
    public boolean updateData(Division division {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("update division set name = ?, set regionId = ? where id = ?");
            preparedStatement.setInt(2, division.getById());
            // preparedStatement.setString(1, division.getRegionId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
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
