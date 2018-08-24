package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/*
*  implementasikan semua method kontrak dari MainService
*
* */

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Employee e) {

    }

    @Override
    public void save(int id) {

    }

    @Override
    public Employee create(Employee e) {
        return null;
    }

    @Override
    public void delete(Employee e) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Employee read(int id) {

        final String sql = "select * from my_table where id = " + id;
        return  jdbcTemplate.queryForObject(sql, Employee.class);
    }
}
