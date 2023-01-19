package com.example.demo.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.Login;
import com.example.demo.dto.Register;
import com.example.demo.dto.ResponsLogin;
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

@Controller
@RequestMapping("user-management")
public class AuthController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, RoleService roleService,
            EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("register")
    public String register(Model model) {
        // model.addAttribute("employee", new Employee());
        // model.addAttribute("user", new User());

        model.addAttribute("register", new Register());

        return "user-management/register";
    }

    @PostMapping("save")
    public String save(Register register) {

        Boolean resultEmployee;
        Boolean resultUser;
        Employee emp = new Employee();
        emp.setFullname(register.getFullname());
        emp.setEmail(register.getEmail());
        emp.setBirthdate(register.getBirthdate());
        resultEmployee = employeeService.save(emp);
        User user = new User();
        user.setId(employeeService.findIdByEmail(register.getEmail()));
        // user.setId(employeeService.findIdByEmail(employee.getEmail()));

        Role role = new Role();
        role.setId(roleService.getIdByLevel());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        resultUser = userService.save(user);

        if (resultEmployee && resultUser) {
            return "redirect:/employee";
        } else {
            return "user-management/register";
        }
    }

    // @PostMapping("save")
    // public String save(Employee employee, User user) {
    // Boolean result;
    // Boolean result2;

    // result = employeeService.save(employee);
    // user.setId(employeeService.findIdByEmail(employee.getEmail()));
    // user.setPassword(passwordEncoder.encode(user.getPassword()));

    // Role role = new Role();
    // role.setId(roleService.getIdByLevel());
    // user.setRole(role);
    // result2 = userService.save(user);
    // if (result && result2) {
    // return "redirect:/employee";
    // } else {
    // return "user-management/register";
    // }

    // }

    // @GetMapping(value = { "login" })
    // public String login(Model model) {
    // model.addAttribute("employee", new Employee());
    // model.addAttribute("user", new User());
    // return "user-management/login";
    // }

    // @PostMapping(value = { "check" })
    // public String check(Employee employee, User user) {
    // Boolean check = employeeService.log(employee.getEmail(), user.getPassword());
    // if (check != null) {

    // return "redirect:/employee";
    // } else {
    // return "user-management/login";
    // }
    // }

    @GetMapping(value = "login")
    public String login(Model model) {
        // model.addAttribute("employee", employee);
        // model.addAttribute("user", user);
        model.addAttribute("login", new Login());
        return "user-management/login";
    }

    @PostMapping(value = { "check" })
    public String authLogin(Login login, Model model) {
        org.springframework.security.core.Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "user-management/user";
        // Object data = userService.login(login);

        // if (data.equals(false)) {
        // model.addAttribute("login", new Login());
        // return "user-management/login";
        // } else {
        // model.addAttribute("users",
        // userService.getById(employeeService.findIdByEmail(login.getEmail())));
        // // model.addAttribute("users", new User());
        // return "redirect:/region";
        // }
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
