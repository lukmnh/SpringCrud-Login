package com.example.demo.models;

import javax.persistence.*;

// annotation
@Entity
// table name from db
@Table(name = "division")
public class Division {
    @Id
    @Column(name = "divisionId")
    // annotation auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer divisionId;
    @Column(name = "divisionName", nullable = false)
    private String divisionName;
    // @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "regionId")
    private String region_id;

    public Integer getDivisionId() {
        return divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public String getRegionId() {
        return region_id;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public void setRegionId(String region_id) {
        this.region_id = region_id;
    }

}
