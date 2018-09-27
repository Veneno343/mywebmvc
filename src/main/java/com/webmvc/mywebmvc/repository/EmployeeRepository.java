package com.webmvc.mywebmvc.repository;

import com.webmvc.mywebmvc.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
