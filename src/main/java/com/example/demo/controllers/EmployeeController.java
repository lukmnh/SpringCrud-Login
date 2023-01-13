package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Register;
import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // @GetMapping()
    // public String index(Model model) {
    // // Object employee = div.getAll();
    // model.addAttribute("employees", employeeService.getAll());
    // // model.addAttribute("user", userService.getAll());
    // // model.addAttribute("role", roleService.getAll());
    // // model.addAttribute("role", roleService.getIdByLevel());
    // return "employee/employee-index";
    // }

    // @GetMapping(value = { "form", "form/{id}" })
    // public String form(@PathVariable(required = false) Integer id, Model model) {
    // if (id != null) {
    // // Object employee = div.getById(id);
    // model.addAttribute("employee", employeeService.getById(id));
    // } else {
    // // model.addAttribute("role", roleService.getIdByLevel());
    // // model.addAttribute("employee", new Employee());
    // // model.addAttribute("user", new User());
    // model.addAttribute("register", new Register());
    // }
    // return "employee/form";
    // }

    // @PostMapping("save")
    // public String save(Register register) {
    // Boolean result;
    // if (register.getId() != null) {
    // result = employeeService.register(register);
    // // result = userService.save(user);
    // } else {
    // result = employeeService.register(register);
    // // result = userService.save(user);
    // }

    // if (result) {
    // return "redirect:/employee";
    // } else {
    // return "employee/form";
    // }
    // }

    // // DELETE
    // @PostMapping(value = "/delete/{id}")
    // public String delete(@PathVariable Integer id) {
    // // employee employee = new employee();

    // // id = employee.setId(id);
    // Boolean result = employeeService.delete(id);
    // if (result) {
    // return "redirect:/employee";
    // } else {
    // return "";
    // }
    // }
    @GetMapping()
    public String index(Model model) {

        model.addAttribute("users", userService.getAll());
        return "employee/employee-index";
    }

    @GetMapping(value = { "form" })
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        return "employee/form";
    }

    @PostMapping("save")
    public String save(Employee employee, User user) {
        Boolean result;
        Boolean result2;

        result = employeeService.save(employee);
        user.setId(employeeService.findIdByEmail(employee.getEmail()));
        // Integer object = employeeService.findIdByEmail(employee.getEmail());
        Role role = new Role();
        role.setId(roleService.getIdByLevel());
        user.setRole(role);
        result2 = userService.save(user);
        if (result && result2) {
            return "redirect:/employee";
        } else {
            return "employee/form";
        }
    }
}
