package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.Login;
import com.example.demo.dto.ResponsLogin;
import com.example.demo.models.Employee;
import com.example.demo.models.User;

public interface UserService {
    public List<User> getAll();

    public User getById(Integer id);

    public Boolean save(User user);

    public Boolean save(User user, Employee employee);

    public User findPassword(String password);

    // public String log(String email, String password);

    public Boolean changePassword(String password, Integer id);

    public Boolean delete(Integer id);

    // public Boolean saveUser(ResponsLogin responsLogin);

    public Object login(Login login);
}
