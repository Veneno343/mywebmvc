package com.salamah.service;

import com.salamah.data.CustomerRepository;
import com.salamah.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger LOG = LogManager.getLogger(CustomUserDetailService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOG.info("username try loggin : " + username);
        Customer cs = customerRepository.findByEmail(username);

        if (cs == null) {
            LOG.error("username not found");
            throw new UsernameNotFoundException("username not found");
        }

        LOG.info("username found : " + username);
        return createUser(cs);
    }

    public void login(Customer customer, HttpServletRequest request)
    {
        SecurityContextHolder.getContext().setAuthentication(authentication(customer));
        request.getSession()
                .setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext());
    }

    private User createUser(Customer customer)
    {
        return new User(customer.getUsername(), customer.getPassword(), getGrantedAuthority(customer));
    }

    private List<GrantedAuthority> getGrantedAuthority(Customer customer)
    {
        return Collections.singletonList(new SimpleGrantedAuthority(customer.getRole().getRole()));
    }

    private Authentication authentication(Customer customer)
    {
        return new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword(),
                getGrantedAuthority(customer));
    }
}
