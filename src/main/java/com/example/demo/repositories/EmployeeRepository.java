package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e.id FROM Employee e WHERE e.email = ?1", nativeQuery = true)
    Integer findIdByEmail(String email);

    @Query(value = "select e.id, e.fullname, e.email, e.birthdate, r.name as roleName from User u join Employee e on u.id = e.id join Role r on u.role_id= r.id where e.email=?1", nativeQuery = true)

    // @Query("select u, r from Employee e join e.User u join e.Role r where email =
    // ?1")
    Employee findEmail(String email);
}
