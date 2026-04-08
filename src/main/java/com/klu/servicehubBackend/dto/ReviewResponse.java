package com.klu.servicehubBackend.dto;

public class ReviewResponse {

    private Long id;
    private Long bookingId;
    private Long serviceId;
    private Long professionalId;
    private String userName;
    private Integer rating;
    private String comment;
    private String createdAt;

    public ReviewResponse() {}

    public ReviewResponse(Long id, Long bookingId, Long serviceId, Long professionalId,
                          String userName, Integer rating, String comment, String createdAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.serviceId = serviceId;
        this.professionalId = professionalId;
        this.userName = userName;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Long getProfessionalId() {
        return professionalId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}