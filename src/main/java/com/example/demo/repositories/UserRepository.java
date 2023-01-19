package com.example.demo.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;
import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // @Query("SELECT u FROM Employee u WHERE u.email = ?1")
    // public Employee getIdByEmail(String email);
    @Query(value = "SELECT u.password FROM user u WHERE u.password = ?1", nativeQuery = true)
    User findPassword(String password);

}
