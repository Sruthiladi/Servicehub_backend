package com.klu.servicehubBackend.dto;

public class AdminDashboardResponse {

    private long totalUsers;
    private long totalProfessionals;
    private long totalBookings;
    private long openTickets;

    public AdminDashboardResponse() {}

    public AdminDashboardResponse(long totalUsers, long totalProfessionals, long totalBookings, long openTickets) {
        this.totalUsers = totalUsers;
        this.totalProfessionals = totalProfessionals;
        this.totalBookings = totalBookings;
        this.openTickets = openTickets;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalProfessionals() {
        return totalProfessionals;
    }

    public void setTotalProfessionals(long totalProfessionals) {
        this.totalProfessionals = totalProfessionals;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getOpenTickets() {
        return openTickets;
    }

    public void setOpenTickets(long openTickets) {
        this.openTickets = openTickets;
    }
}