package com.example.demo.models;

import javax.persistence.*;

// annotation
@Entity
// table name from db
@Table(name = "division")
public class Division {
    @Id
    @Column(name = "id")
    // annotation auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "regionId", referencedColumnName = "id")
    private Region region;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Region getRegion() {
        return region;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
