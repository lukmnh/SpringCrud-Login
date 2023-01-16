package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
// import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
// import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user-management")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    UserService userService;

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());

        return "user-management/register";
    }

    @PostMapping("save")
    public String save(Employee employee, User user) {
        Boolean result;
        Boolean result2;
        String email;

        email = employeeService.getEmail(employee.getEmail());
        if (email == null) {
            result = employeeService.save(employee);
            user.setId(employeeService.findIdByEmail(employee.getEmail()));

            Role role = new Role();
            role.setId(roleService.getIdByLevel());
            user.setRole(role);
            result2 = userService.save(user);
            if (result && result2) {
                return "redirect:/employee";
            } else {
                return "user-management/register";
            }
        } else {
            return "user-management/register";
        }

    }

    @GetMapping(value = { "login" })
    public String login(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        return "user-management/login";
    }

    @PostMapping(value = { "check" })
    public String check(Employee employee, User user) {
        String check = employeeService.log(employee.getEmail(), user.getPassword());
        if (check != null) {

            return "redirect:/employee/employee-index";
        } else {
            return "user-management/login";
        }
    }

    @GetMapping(value = { "changePassword/{id}" })
    public String create(@PathVariable(required = false) Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user-management/changePassword";
    }

    @PostMapping(value = { "updatePassword" })
    public String updateLogin(User user) {
        Boolean result;
        result = userService.changePassword(user.getPassword(), user.getId());
        if (result) {
            return "redirect:/employee";
        } else {
            return "user-management/changePassword";
        }
    }

    @GetMapping(value = { "forgotPassword" })
    public String forgotPassword(Model model) {
        model.addAttribute("employee", new Employee());
        return "user-management/forgotPassword";
    }

    @PostMapping(value = { "checkEmail" })
    public String checkEmail(Employee employee, Model model) {
        Integer id;
        id = employeeService.findIdByEmail(employee.getEmail());
        if (id != null) {
            model.addAttribute("user", userService.getById(id));
            return "user-management/changePassword";
        } else {
            return "user-management/forgotPassword";
        }
    }
}
