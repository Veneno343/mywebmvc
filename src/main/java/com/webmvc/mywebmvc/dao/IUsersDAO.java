package com.webmvc.mywebmvc.dao;

import com.webmvc.mywebmvc.model.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsersDAO {
    void save(Users users);

    Users getUser(String username);
}
