package com.example.demo.repositories;

// import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select r.id from Role r where r.level = (select max(level) from Role )", nativeQuery = true)
    Integer getIdByMaxLevel();
}
