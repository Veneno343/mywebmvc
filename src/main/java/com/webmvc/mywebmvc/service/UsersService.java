package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.dao.IUsersDAO;
import com.webmvc.mywebmvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements IUsersService{
    IUsersDAO usersDAO;

    @Autowired
    public void SetUsersDAO(IUsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public void save(Users users) {
        usersDAO.save(users);
    }
}
