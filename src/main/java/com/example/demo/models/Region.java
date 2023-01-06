package com.example.demo.models;

import javax.persistence.*;

// annotation
@Entity
// table name from db
@Table(name = "region")
public class Region {
    @Id
    // ID auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regionId")
    private Integer regionId;
    @Column(name = "regionName", nullable = false)
    private String regionName;

    public Integer getRegionId() {
        return regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
