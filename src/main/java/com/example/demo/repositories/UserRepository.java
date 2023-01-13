package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Employee;
import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query("SELECT u FROM Employee u WHERE u.email = ?1")
    // public Employee getIdByEmail(String email);
}
