package com.webmvc.mywebmvc.dao;

import com.webmvc.mywebmvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
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
        final String sql = "INSERT INTO Users(username,password,enabled) VALUES (?,?,1)";
        jdbcTemplate.update(sql,new Object[]{users.getUsername(),passwordEncoder.encode(users.getPassword())});
    }
}
