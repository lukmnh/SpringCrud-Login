package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

// annotation
@Entity
// table name from db
@Table(name = "role")
public class Role {
    @Id
    // ID auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "level", nullable = false)
    private String level;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
