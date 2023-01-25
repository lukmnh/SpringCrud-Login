package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public RestEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(required = true) Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping("/insert")
    public Boolean insert(@RequestBody Employee employee) {
        Boolean result = employeeService.save(employee);
        return result;
    }

    @PutMapping("/update/{id}")
    public Boolean update(@PathVariable(required = true) Integer id, @RequestBody Employee employee) {
        employee.setId(id);
        Boolean result = employeeService.save(employee);
        return result;
    }

    @DeleteMapping("/{id}")
    public Boolean update(@PathVariable(required = true) Integer id) {
        Boolean result = employeeService.delete(id);
        return result;
    }
}
