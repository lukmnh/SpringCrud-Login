package com.example.demo.services;

import java.util.List;

// import com.example.demo.dto.Register;
import com.example.demo.models.Employee;
// import com.example.demo.models.User;

public interface EmployeeService {
    public List<Employee> getAll();

    public Employee getById(Integer id);

    public Integer findIdByEmail(String email);

    public Boolean save(Employee employee);

    public Boolean delete(Integer id);

    public String log(String email, String password);

    public String getEmail(String email);
}
