package com.klu.servicehubBackend.dto;

public class AdminServiceResponse {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private String duration;
    private String status;
    private String professionalName;

    public AdminServiceResponse() {}

    public AdminServiceResponse(Long id, String name, String category, Double price, String duration, String status, String professionalName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
        this.status = status;
        this.professionalName = professionalName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }
}