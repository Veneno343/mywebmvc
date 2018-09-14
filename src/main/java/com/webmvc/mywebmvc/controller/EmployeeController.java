package com.webmvc.mywebmvc.controller;

import com.webmvc.mywebmvc.model.Employee;
import com.webmvc.mywebmvc.model.Users;
import com.webmvc.mywebmvc.service.IEmployeeService;
import com.webmvc.mywebmvc.service.IUsersService;
import org.apache.log4j.Logger;
import org.h2.engine.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.cache.annotation.CacheResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class EmployeeController {

    private final Logger logger = Logger.getLogger(EmployeeController.class.getName());
    @Autowired
    private IEmployeeService IEmployeeService;

    @Autowired
    private IUsersService IUsersService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("person","Jonathan!");
        model.addAttribute("behavior","Programmer");
        logger.debug("Requesting Index Page");
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value ="error", required = false)String error,@RequestParam(value="logout", required =false)String logout,
                        Model model) {

        String errMsg = null;
        if (error != null) {
            errMsg = "Username or Password is not found";
        }

        if (logout != null) {
            errMsg = "Successfully logged out";
        }

        model.addAttribute("users",new Users());
        model.addAttribute("message",errMsg);

        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("users",new Users());

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid Users users,BindingResult result) {
        if(result.hasErrors()) {
            return "signup";
        } else {
            IUsersService.save(users);
            return "redirect:/index";
        }

    }

    @GetMapping("/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout=true";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String curUser = authentication.getName();

        model.addAttribute("employee",new Employee());
        model.addAttribute("username",curUser);

        return "empview/manage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String curUser = authentication.getName();

        model.addAttribute("employee", IEmployeeService.read(id));
        model.addAttribute("username",curUser);

        logger.info("Accessing Edit Page");

        return "empview/manage";
    }

    @PostMapping("/save")
    public String save(@Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "empview/manage";
        } else {
            if((employee != null) && (employee.getId() != null)) {
                IEmployeeService.update(employee);
            } else {
                IEmployeeService.save(employee);
            }

            return "redirect:/report";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        IEmployeeService.delete(id);

        return "redirect:/report";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("employee", IEmployeeService.readAll());

        logger.info("Accessing report page");
        return "empview/result";
    }

    @GetMapping("/403")
    public String denied(Model model) {
        String curUser;
        String curRole;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            curUser = authentication.getName();
            curRole = authentication.getAuthorities().toString();

            model.addAttribute("role",curRole);
            model.addAttribute("username",curUser);
        }

        logger.error("Denied access!");

        return "403";
    }
}
