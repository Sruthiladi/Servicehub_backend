package com.klu.servicehubBackend.dto;

public class AdminProfessionalResponse {

    private Long id;
    private String name;
    private String email;
    private String category;
    private String status;

    public AdminProfessionalResponse() {}

    public AdminProfessionalResponse(Long id, String name, String email, String category, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.category = category;
        this.status = status;
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

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}