package com.klu.servicehubBackend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.servicehubBackend.dto.ProfessionalBookingResponse;
import com.klu.servicehubBackend.dto.ProfessionalDashboardResponse;
import com.klu.servicehubBackend.dto.ProfessionalEarningsResponse;
import com.klu.servicehubBackend.entity.BookingEntity;
import com.klu.servicehubBackend.repository.BookingRepository;

@Service
public class ProfessionalService {

    private final BookingRepository bookingRepository;

    public ProfessionalService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ProfessionalDashboardResponse getDashboard(Long professionalId) {
        List<BookingEntity> bookings = bookingRepository.findByProfessionalId(professionalId);

        long totalBookings = bookings.size();
        long pendingRequests = bookings.stream()
                .filter(b -> "Pending".equalsIgnoreCase(b.getStatus()))
                .count();

        long completedJobs = bookings.stream()
                .filter(b -> "Completed".equalsIgnoreCase(b.getStatus()))
                .count();

        double totalEarnings = bookings.stream()
                .filter(b -> "Completed".equalsIgnoreCase(b.getStatus()))
                .mapToDouble(b -> b.getAmount() != null ? b.getAmount() : 0.0)
                .sum();

        List<ProfessionalBookingResponse> recentBookings = new ArrayList<>();

        for (BookingEntity booking : bookings) {
            recentBookings.add(new ProfessionalBookingResponse(
                    booking.getId(),
                    booking.getUser() != null ? booking.getUser().getName() : "-",
                    booking.getService() != null ? booking.getService().getName() : "-",
                    booking.getBookingDate(),
                    booking.getBookingTime(),
                    booking.getStatus(),
                    booking.getAmount()
            ));
        }

        Collections.reverse(recentBookings);

        if (recentBookings.size() > 5) {
            recentBookings = recentBookings.subList(0, 5);
        }

        return new ProfessionalDashboardResponse(
                totalBookings,
                pendingRequests,
                completedJobs,
                totalEarnings,
                recentBookings
        );
    }

    public ProfessionalEarningsResponse getEarnings(Long professionalId) {
        List<BookingEntity> bookings = bookingRepository.findByProfessionalId(professionalId);

        double totalEarnings = bookings.stream()
                .filter(b -> "Completed".equalsIgnoreCase(b.getStatus()))
                .mapToDouble(b -> b.getAmount() != null ? b.getAmount() : 0.0)
                .sum();

        long completedJobs = bookings.stream()
                .filter(b -> "Completed".equalsIgnoreCase(b.getStatus()))
                .count();

        long pendingJobs = bookings.stream()
                .filter(b -> "Pending".equalsIgnoreCase(b.getStatus()) || "Accepted".equalsIgnoreCase(b.getStatus()))
                .count();

        double pendingPayouts = bookings.stream()
                .filter(b -> "Pending".equalsIgnoreCase(b.getStatus()) || "Accepted".equalsIgnoreCase(b.getStatus()))
                .mapToDouble(b -> b.getAmount() != null ? b.getAmount() : 0.0)
                .sum();

        return new ProfessionalEarningsResponse(
                totalEarnings,
                completedJobs,
                pendingJobs,
                pendingPayouts
        );
    }

    public List<ProfessionalBookingResponse> getEarningBookings(Long professionalId) {
        List<BookingEntity> bookings = bookingRepository.findByProfessionalId(professionalId);
        List<ProfessionalBookingResponse> response = new ArrayList<>();

        for (BookingEntity booking : bookings) {
            response.add(new ProfessionalBookingResponse(
                    booking.getId(),
                    booking.getUser() != null ? booking.getUser().getName() : "-",
                    booking.getService() != null ? booking.getService().getName() : "-",
                    booking.getBookingDate(),
                    booking.getBookingTime(),
                    booking.getStatus(),
                    booking.getAmount()
            ));
        }

        Collections.reverse(response);
        return response;
    }
}