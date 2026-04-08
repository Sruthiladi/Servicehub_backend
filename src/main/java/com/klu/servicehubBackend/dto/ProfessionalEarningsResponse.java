package com.klu.servicehubBackend.dto;

public class ProfessionalEarningsResponse {

    private double totalEarnings;
    private long completedJobs;
    private long pendingJobs;
    private double pendingPayouts;

    public ProfessionalEarningsResponse() {}

    public ProfessionalEarningsResponse(double totalEarnings, long completedJobs, long pendingJobs, double pendingPayouts) {
        this.totalEarnings = totalEarnings;
        this.completedJobs = completedJobs;
        this.pendingJobs = pendingJobs;
        this.pendingPayouts = pendingPayouts;
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public long getCompletedJobs() {
        return completedJobs;
    }

    public void setCompletedJobs(long completedJobs) {
        this.completedJobs = completedJobs;
    }

    public long getPendingJobs() {
        return pendingJobs;
    }

    public void setPendingJobs(long pendingJobs) {
        this.pendingJobs = pendingJobs;
    }

    public double getPendingPayouts() {
        return pendingPayouts;
    }

    public void setPendingPayouts(double pendingPayouts) {
        this.pendingPayouts = pendingPayouts;
    }
}