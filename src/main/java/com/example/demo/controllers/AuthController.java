package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
// import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
// import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.services.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String login(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());

        return "user-management/login";
    }

    @PostMapping(value = "{check}")
    public String check(Employee employee, User user) {
        String check = employeeService.log(employee.getEmail(), user.getPassword());
        if (check != null) {

            return "redirect:/employee";
        } else {
            return "user-management/login";
        }
    }

    @GetMapping("forgotPassword")
    public String forgotPassword(Employee employee, User user) {
        return "";
    }
}
