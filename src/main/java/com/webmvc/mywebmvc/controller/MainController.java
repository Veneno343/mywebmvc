package com.webmvc.mywebmvc.controller;

import com.webmvc.mywebmvc.model.Employee;
import com.webmvc.mywebmvc.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("person","Jonathan!");
        model.addAttribute("behavior","Programmer");
        return "index";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("employee",mainService.readAll());

        return "empview/result";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee",new Employee());

        return "empview/add";
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        mainService.save(employee);

        return "empview/result";
    }
}
