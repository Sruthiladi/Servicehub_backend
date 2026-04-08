package com.klu.servicehubBackend.dto;

import java.util.List;

public class ProfessionalDashboardResponse {

    private long totalBookings;
    private long pendingRequests;
    private long completedJobs;
    private double totalEarnings;
    private List<ProfessionalBookingResponse> recentBookings;

    public ProfessionalDashboardResponse() {}

    public ProfessionalDashboardResponse(long totalBookings, long pendingRequests, long completedJobs,
                                         double totalEarnings, List<ProfessionalBookingResponse> recentBookings) {
        this.totalBookings = totalBookings;
        this.pendingRequests = pendingRequests;
        this.completedJobs = completedJobs;
        this.totalEarnings = totalEarnings;
        this.recentBookings = recentBookings;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public long getCompletedJobs() {
        return completedJobs;
    }

    public void setCompletedJobs(long completedJobs) {
        this.completedJobs = completedJobs;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public List<ProfessionalBookingResponse> getRecentBookings() {
        return recentBookings;
    }

    public void setRecentBookings(List<ProfessionalBookingResponse> recentBookings) {
        this.recentBookings = recentBookings;
    }
}