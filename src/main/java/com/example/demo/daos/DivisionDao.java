package com.example.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Division;

public class DivisionDao {
    private Connection conn;

    public DivisionDao(Connection conn) {
        this.conn = conn;
    }

    // get all data from table division
    public List<Division> getAll() {
        List<Division> division = new ArrayList<>();
        String query = "Select d.divisionId, d.divisionName, r.regionName from division d join region r  on d.region_id = r.regionId order by d.divisionId asc";
        try {
            ResultSet resultSet = conn.prepareStatement(query).executeQuery();
            while (resultSet.next()) {
                Division div = new Division();
                div.setDivisionId(resultSet.getInt(1));
                div.setDivisionName(resultSet.getString(2));
                div.setRegionId(resultSet.getString(3));
                division.add(div);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return division;
    }

    // get data by id from table division
    public Division getById(Integer divisionId) {
        Division divId = new Division();
        String que = "select * from division where divisionId = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(que);
            preparedStatement.setInt(1, divisionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                divId.setDivisionId(resultSet.getInt(1));
                divId.setDivisionName(resultSet.getString(2));
                divId.setRegionId(resultSet.getString(3));

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
                    .prepareStatement("insert into division(divisionId, divisionName, region_id) values(?,?,?)");
            preparedStatement.setInt(1, division.getDivisionId());
            preparedStatement.setString(2, division.getDivisionName());
            preparedStatement.setString(3, division.getRegionId());
            int temp = preparedStatement.executeUpdate();
            return temp > 0;

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    // update data on table division
    public boolean updateData(Division division) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("update division set region_id = ? where divisionId = ?");
            preparedStatement.setInt(2, division.getDivisionId());
            preparedStatement.setString(1, division.getRegionId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteData(Division division) {
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("delete from division where divisionId = ?");
            preparedStatement.setInt(1, division.getDivisionId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
}
