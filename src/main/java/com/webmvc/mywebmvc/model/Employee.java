package com.webmvc.mywebmvc.model;

import javax.annotation.Generated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * Base class untuk model Employee
 */
public class Employee {


    private Integer id;

    @NotNull
    @Size(min = 5, max = 35, message = "{employee.name.size}")
    private String name;

    @NotNull(message = "{employee.age.check}")
    @Min(value = 20, message = "{employee.age.value}")
    @Max(value = 40, message = "{employee.age.value}")
    private Integer age;

    @NotNull
    @Size(min = 5, max = 25, message = "{employee.city.size}")
    private String city;

    private String country;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
