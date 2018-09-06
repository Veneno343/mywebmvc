package com.webmvc.mywebmvc.service;

import com.webmvc.mywebmvc.dao.IUsersDAO;
import com.webmvc.mywebmvc.dao.UsersDAO;
import com.webmvc.mywebmvc.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AuthService implements UserDetailsService{

    @Autowired
    private IUsersDAO usersDAO;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        Users userDetails = (Users) usersDAO.getUser(username);

        if(userDetails == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userDetails.getRole());
        UserDetails details = new User(userDetails.getUsername(),userDetails.getPassword(), Arrays.asList(grantedAuthority));

        return details;
    }
}
