package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Employee;
import com.example.demo.models.User;

public interface UserService {
    public List<User> getAll();

    public User getById(Integer id);

    public Boolean save(User user);

    // public Boolean saveLevel(User user);

    public Boolean delete(Integer id);
}
