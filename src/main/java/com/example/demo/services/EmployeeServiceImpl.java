package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Register;
import com.example.demo.dto.ResponsLogin;
import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserManagementRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("employee not found"));
    }

    @Override
    public Boolean delete(Integer id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.findById(id).isPresent();
    }

    @Override
    public Boolean register(Register register) {
        Employee employee = new Employee();
        User user = new User();
        boolean result = employeeService.save(employee);
        user.setId(employeeService.findIdByEmail(employee.getEmail()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setId(roleService.getIdByLevel());
        user.setRole(role);
        Boolean result2 = userService.save(user);

        if (result && result2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer findIdByEmail(String email) {
        return employeeRepository.findIdByEmail(email);
    }

    @Override
    public Boolean save(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).isPresent();
    }

    @Override
    public ResponsLogin log(String email, String password) {
        Object data = userManagementRepository.log(email, password);
        return userManagementRepository.log(email, password);
    }

    @Override
    public Employee findEmail(String email) {
        return employeeRepository.findEmail(email);
    }

}
