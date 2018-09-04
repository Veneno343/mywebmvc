package com.webmvc.mywebmvc.controller;

import com.webmvc.mywebmvc.model.Employee;
import com.webmvc.mywebmvc.model.Users;
import com.webmvc.mywebmvc.service.IEmployeeService;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService IEmployeeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("person","Jonathan!");
        model.addAttribute("behavior","Programmer");
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

    @PostMapping("/login")
    public String login(Model model,Users users) {
        model.addAttribute("password",users.getPassword());

        return "login";
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

        return "empview/result";
    }

    @GetMapping("/403")
    public String denied(Model model) {

        return "403";
    }
}
