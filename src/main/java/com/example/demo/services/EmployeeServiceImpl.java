package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Register;
import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

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

    // @Override
    // public Boolean register(Register register) {
    // // TODO Auto-generated method stub
    // return true;
    // }

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
    public String log(String email, String password) {

        return employeeRepository.log(email, password);
    }

    @Override
    public String getEmail(String email) {
        return employeeRepository.getEmail(email);
    }

}
