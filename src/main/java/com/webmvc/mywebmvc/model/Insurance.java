package com.webmvc.mywebmvc.model;

import java.io.Serializable;
import java.util.Date;

public class Insurance implements Serializable {

    private Integer id;

    private String code;
    private Integer contract_id;
    private Date start_date;
    private Date end_date;
}
