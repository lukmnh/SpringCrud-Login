package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e.id FROM Employee e WHERE e.email = ?1", nativeQuery = true)
    public Integer findIdByEmail(String email);

    @Query(value = "SELECT e.email, u.password from Employee e join User u on e.id = u.id where e.email = ?1 && u.password = ?2 ", nativeQuery = true)
    public String log(String email, String password);
}
