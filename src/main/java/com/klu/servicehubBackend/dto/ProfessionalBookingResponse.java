package com.klu.servicehubBackend.dto;

public class ProfessionalBookingResponse {

    private Long id;
    private String customerName;
    private String serviceName;
    private String bookingDate;
    private String bookingTime;
    private String status;
    private Double amount;

    public ProfessionalBookingResponse() {}

    public ProfessionalBookingResponse(Long id, String customerName, String serviceName,
                                       String bookingDate, String bookingTime,
                                       String status, Double amount) {
        this.id = id;
        this.customerName = customerName;
        this.serviceName = serviceName;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.status = status;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}