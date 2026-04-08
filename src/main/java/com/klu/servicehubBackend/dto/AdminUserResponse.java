package com.klu.servicehubBackend.dto;

public class AdminUserResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
    private String status;
    private String joined;

    public AdminUserResponse() {}

    public AdminUserResponse(Long id, String name, String email, String role, String status, String joined) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
        this.joined = joined;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getJoined() {
        return joined;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }
}