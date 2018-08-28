package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.dao.IEmployeeDAO;
import com.webmvc.mywebmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
*   Implementasi dari Interface IEmployeeService
*   Class untuk handle Business Logic untuk aplikasi
*
* */

@Service
public class EmployeeService implements IEmployeeService {

    IEmployeeDAO employeeDAO;

    @Autowired
    public void SetEmployeeDao(IEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void save(Employee e) {
        employeeDAO.save(e);
    }

    @Override
    public void save(Integer id) {

    }

    @Override
    public void update(Employee e) {
        employeeDAO.update(e);
    }

    @Override
    public Employee create(Employee e) {
        return null;
    }

    @Override
    public void delete(Employee e) {
        employeeDAO.delete(e);
    }

    @Override
    public void delete(Integer id) {
        employeeDAO.delete(id);
    }


    @Override
    public Employee read(Integer id) {
        return employeeDAO.read(id);
    }

    @Override
    public List readAll() {
        return employeeDAO.readAll();
    }
}
