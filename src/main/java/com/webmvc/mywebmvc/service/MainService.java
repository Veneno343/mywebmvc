package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.model.Employee;

import java.util.ArrayList;
import java.util.List;

/*
*  base service untuk crud
*
* */
public interface MainService {

    void save(Employee e);

    void save(int id);

    Employee create(Employee e);

    void delete(Employee e);

    void delete(int id);

    Employee read(int id);

    List<Employee> readAll();
}
