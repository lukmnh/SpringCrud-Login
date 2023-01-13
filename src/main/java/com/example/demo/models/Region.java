package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

// annotation
@Entity
// table name from db
@Table(name = "region")
public class Region {
    @Id
    // ID auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @Column(name = "name", nullable = false)
    private String Name;

    @OneToMany(mappedBy = "region")
    private List<Division> division;

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
