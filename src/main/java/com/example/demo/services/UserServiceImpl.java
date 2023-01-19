package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Login;
import com.example.demo.dto.ResponsLogin;
import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserManagementRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public Boolean save(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    @Override
    public User findPassword(String password) {
        return userRepository.findPassword(password);
    }

    @Override
    public Boolean changePassword(String password, Integer id) {
        userManagementRepository.changePassword(password, id);
        return userRepository.findById(id).isPresent();
    }

    // @Override
    // public String log(String email, String password) {
    // // TODO Auto-generated method stub
    // return null;
    // }

    // @Override
    // public Boolean saveUser(ResponsLogin responsLogin) {

    // return null;
    // }

    @Override
    public Object login(Login login) {
        Employee employee = employeeRepository.findEmail(login.getEmail());
        if (employee.getEmail() != null
                && passwordEncoder.matches(login.getPassword(), employee.getUser().getPassword())) {

            return login;
        }

        return false;
    }

    @Override
    public Boolean save(User user, Employee employee) {
        Role role = new Role();
        user.setRole(role);
        role.setId(roleService.getIdByLevel());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        employee.setId(user.getId());
        employeeRepository.save(employee);
        return userRepository.findById(user.getId()).isPresent();
    }

}
