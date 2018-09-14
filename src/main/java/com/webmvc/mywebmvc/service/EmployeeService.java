package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.dao.IEmployeeDAO;
import com.webmvc.mywebmvc.model.Employee;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CachePut;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CacheValue;
import java.util.List;

/*
*   Implementasi dari Interface IEmployeeService
*   Class untuk handle Business Logic untuk aplikasi
*
* */

@Service
public class EmployeeService implements IEmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());
    IEmployeeDAO employeeDAO;

    @Autowired
    public void SetEmployeeDao(IEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override

    public void save(@CacheValue  Employee e) {
        employeeDAO.save(e);
    }

    @Override
    public void save(Integer id) {
    }

    @Override
    @CachePut(cacheName = "employee")
    public void update(@CacheValue Employee e) {
        logger.info("<!-- Invoking Method - Saving Update Cache with ID : " + e.getId());

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
        logger.info("<---! Flush Seleceted ID {} !--->" + id);

        employeeDAO.delete(id);
    }


    @Override
    @CacheResult(cacheName = "employee")
    public Employee read(@CacheKey Integer id) {
        logger.info("Invoking Method - New Cached ID");

        return employeeDAO.read(id);
    }

    @Override
    public List readAll() {
        return employeeDAO.readAll();
    }
}