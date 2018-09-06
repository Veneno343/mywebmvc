package com.salamah.conf;

import com.salamah.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    private CustomUserDetailService customUserDetailService;
    private static final String[] ALLOW_URI = {
            "/", "/static/**", "/signup", "/contactus", "/shop", "/contactus/success",
            "/subscribe", "/subscribe/subscribe-confirm", "/shop/product/{id}", "/checkout",
            "/checkout/remove", "/checkout/removeAll", "/search", "/search/item/{pageNum}", "/listBarang",
            "/createBarang", "/updateBarang", "/barangEdit", "/barang", "/removeBarang", "/searchBarang", "/searchMyBarang"
    } ;

    @Autowired
    public void setCustomUserDetailService(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenBasedRememberMeServices meServices()
    {
        return new TokenBasedRememberMeServices("please-remember-me", customUserDetailService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.eraseCredentials(true)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ALLOW_URI)
                .permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/signin")
                .permitAll()
                .failureUrl("/signin?error=1")
                .loginProcessingUrl("/authenticate").and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessUrl("/signin?out")
                .and()
                .rememberMe()
                .rememberMeServices(meServices())
                .key("please-remember-me");
    }
}
