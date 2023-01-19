package com.example.demo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ResponsLogin;
import com.example.demo.models.Employee;
import com.example.demo.models.User;

@Repository
public interface UserManagementRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE user u SET u.password = :password WHERE u.id = :id", nativeQuery = true)
    public boolean changePassword(@Param("password") String password, @Param("id") Integer id);

    @Query(value = "select e.id, e.fullname, e.email, r.name as roleName from User u join Employee e on u.id = e.id join Role r on u.role_id = r.id where e.email =?1 && u.password = ?2", nativeQuery = true)

    ResponsLogin log(String email, String password);
}
