package com.example.demo.dto;

public class ResponsLogin {

    private Integer id;
    private String fullname;
    // private String email;
    // private String roleName;

    public ResponsLogin(Integer id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getFullName() {
        return fullname;
    }

    // public void setEmail(String email) {
    // this.email = email;
    // }

    // public String getEmail() {
    // return email;
    // }

    // public void setRoleName(String roleName) {
    // this.roleName = roleName;
    // }

    // public String getRoleName() {
    // return roleName;
    // }

}
