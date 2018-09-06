package com.webmvc.mywebmvc.dao;

import com.webmvc.mywebmvc.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet resultSet, int row) throws SQLException {
        Users userDetails = new Users();
        userDetails.setUsername(resultSet.getString("username"));
        userDetails.setPassword(resultSet.getString("password"));
        userDetails.setRole(resultSet.getString("role"));

        return userDetails;
    }
}
