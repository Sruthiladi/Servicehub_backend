package com.klu.servicehubBackend.dto;

public class SupportTicketResponse {
    private Long id;
    private String userName;
    private String subject;
    private String category;
    private String message;
    private String priority;
    private String status;
    private String createdAt;
    private String resolvedAt;
    private String resolvedBy;

    public SupportTicketResponse() {}

    public SupportTicketResponse(Long id, String userName, String subject, String category, String message,
                                 String priority, String status, String createdAt,
                                 String resolvedAt, String resolvedBy) {
        this.id = id;
        this.userName = userName;
        this.subject = subject;
        this.category = category;
        this.message = message;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.resolvedAt = resolvedAt;
        this.resolvedBy = resolvedBy;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getSubject() {
        return subject;
    }

    public String getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getResolvedAt() {
        return resolvedAt;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }
}