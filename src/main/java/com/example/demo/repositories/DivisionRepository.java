package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Division;

public interface DivisionRepository extends JpaRepository<Division, Integer> {

}
