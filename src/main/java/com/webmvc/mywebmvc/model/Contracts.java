package com.webmvc.mywebmvc.model;

import java.io.Serializable;
import java.sql.Date;

public class Contracts implements Serializable {

    private Integer id;

    private Integer emp_id;
    private String code;
    private String assignment;
    private Date start_date;
    private Date end_date;
}
