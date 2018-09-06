package com.webmvc.mywebmvc.dao;

import com.webmvc.mywebmvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UsersDAO implements IUsersDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void save(Users users) {
        final String sql = "INSERT INTO Users(username,password,enabled) VALUES (?,?,true)";
        jdbcTemplate.update(sql,new Object[]{users.getUsername(),passwordEncoder.encode(users.getPassword())});
    }

    @Override
    public Users getUser(String username) {
        try {
            final String sql = "select u.username as username,u.password as password,a.authority as role FROM users AS u,authorities AS a WHERE u.username = ? AND u.username = a.username";
            Users userDetails = jdbcTemplate.queryForObject(sql, new Object[] { username }, new UserRowMapper());

            return userDetails;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
