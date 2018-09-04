package com.webmvc.mywebmvc.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Users {
    @Autowired
    PasswordEncoder passwordEncoder;

    @NotNull
    @Size(min = 5, max = 25, message = "Username must be within {min} and {max} characters")
    private String username;

    @NotNull
    @Size(min = 5, max = 50, message = "Password must be within {min} and {max} characters")
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
