package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.model.Employee;

import java.util.ArrayList;
import java.util.List;

/*
*  Base Service untuk aplikasi
*
* */
public interface IEmployeeService {

    void save(Employee e);

    void save(Integer id);

    void update(Employee e);

    Employee create(Employee e);

    void delete(Employee e);

    void delete(Integer id);

    Employee read(Integer id);

    List readAll();
}
