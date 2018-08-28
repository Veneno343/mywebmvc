package com.webmvc.mywebmvc.dao;

import com.webmvc.mywebmvc.model.Employee;

import java.util.List;

/*
 * Base class DAO untuk Employee
 * Inherit oleh EmployeeDAO
 */
public interface IEmployeeDAO {
    void save(Employee e);

    void save(Integer id);

    void update(Employee e);

    Employee create(Employee e);

    void delete(Employee e);

    void delete(Integer id);

    Employee read(Integer id);

    List<Employee> readAll();
}
