package com.webmvc.mywebmvc.controller;

import com.webmvc.mywebmvc.model.Employee;
import com.webmvc.mywebmvc.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee",new Employee());

        return "empview/add";
    }

    @PostMapping("/add")
    public String save(Employee employee) {
        mainService.save(employee);

        return "redirect:/report";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id,Model model) {
        model.addAttribute("employee",mainService.read(id));

        return "empview/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, Employee employee) {
        mainService.save(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        mainService.delete(id);

        return "redirect:/report";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("employee",mainService.readAll());

        return "empview/result";
    }
}
