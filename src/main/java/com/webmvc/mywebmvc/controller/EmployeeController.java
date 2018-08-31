package com.webmvc.mywebmvc.controller;

import com.webmvc.mywebmvc.model.Employee;
import com.webmvc.mywebmvc.model.Users;
import com.webmvc.mywebmvc.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String login(Model model, Users users) {
        model.addAttribute("users",users);
        return "login";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee",new Employee());

        return "empview/manage";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,Model model) {
        model.addAttribute("employee", IEmployeeService.read(id));

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
