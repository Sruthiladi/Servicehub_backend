package com.klu.servicehubBackend.dto;

public class BookingResponse {
    private Long id;
    private Long serviceId;
    private Long professionalId;

    private String serviceName;
    private String professionalName;
    private String customerName;
    private String bookingDate;
    private String bookingTime;
    private String paymentMethod;
    private Double amount;
    private String status;
    private String notes;

    public BookingResponse() {}

    public BookingResponse(Long id, Long serviceId, Long professionalId,
                           String serviceName, String professionalName, String customerName,
                           String bookingDate, String bookingTime, String paymentMethod,
                           Double amount, String status, String notes) {
        this.id = id;
        this.serviceId = serviceId;
        this.professionalId = professionalId;
        this.serviceName = serviceName;
        this.professionalName = professionalName;
        this.customerName = customerName;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Long getProfessionalId() {
        return professionalId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }
}