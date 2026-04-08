package com.klu.servicehubBackend.dto;

public class ServiceResponse {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private String duration;
    private String status;
    private Long professionalId;
    private String professionalName;

    public ServiceResponse() {}

    public ServiceResponse(Long id, String name, String category, Double price, String duration,
                           String status, Long professionalId, String professionalName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.duration = duration;
        this.status = status;
        this.professionalId = professionalId;
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

    public Long getProfessionalId() {
        return professionalId;
    }

    public String getProfessionalName() {
        return professionalName;
    }
}