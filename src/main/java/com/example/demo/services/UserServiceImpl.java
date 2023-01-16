package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleService roleService;

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
    public String findPassword(String password) {
        return userRepository.findPassword(password);
    }

    @Override
    public Boolean changePassword(String password, Integer id) {
        userRepository.changePassword(password, id);
        return userRepository.findById(id).isPresent();
    }

}
