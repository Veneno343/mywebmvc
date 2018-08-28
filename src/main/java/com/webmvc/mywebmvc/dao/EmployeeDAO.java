package com.webmvc.mywebmvc.dao;

import com.webmvc.mywebmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*
 * Class implementasi dari Interface IEmployeeDAO
 * Handle operasi-operasi Database
 *
 */
@Repository
public class EmployeeDAO implements IEmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Employee e) {
        final String sql = "INSERT INTO Employee(name,age,city,country) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{e.getName(),e.getAge(),e.getCity(),e.getCountry()});
    }

    @Override
    public void save(Integer id) {

    }

    @Override
    public void update(Employee e) {
        final String sql = "UPDATE Employee SET name = ?,age = ?,city = ?,country = ? WHERE id = ?";
        jdbcTemplate.update(sql,new Object[] {e.getName(),e.getAge(),e.getCity(),e.getCountry(),e.getId()} );
    }

    @Override
    public Employee create(Employee e) {
        return null;
    }

    @Override
    public void delete(Employee e) {

    }

    @Override
    public void delete(Integer id) {
        final String sql = "DELETE FROM Employee WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }


    @Override
    public Employee read(Integer id) {

        final String sql = "SELECT * FROM Employee WHERE id = " + id;
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Employee.class));
    }

    @Override
    public List<Employee> readAll() {

        final String sql = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<Employee>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            Employee employee = new Employee();

            employee.setId((Integer)(row.get("id")));
            employee.setName((String)(row.get("name")));
            employee.setAge((Integer) (row.get("age")));
            employee.setCity((String)(row.get("city")));
            employee.setCountry((String)(row.get("country")));

            employees.add(employee);
        }

        return employees;
    }
}
