package com.klu.servicehubBackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.servicehubBackend.dto.BookingRequest;
import com.klu.servicehubBackend.dto.BookingResponse;
import com.klu.servicehubBackend.entity.BookingEntity;
import com.klu.servicehubBackend.entity.ServiceEntity;
import com.klu.servicehubBackend.entity.User;
import com.klu.servicehubBackend.repository.BookingRepository;
import com.klu.servicehubBackend.repository.ServiceRepository;
import com.klu.servicehubBackend.repository.UserRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    public BookingResponse createBooking(BookingRequest request) {
        ServiceEntity service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User professional = service.getProfessional();

        BookingEntity booking = new BookingEntity(
                request.getBookingDate(),
                request.getBookingTime(),
                request.getNotes(),
                request.getPaymentMethod(),
                service.getPrice() + 49,
                "Pending",
                service,
                user,
                professional
        );

        BookingEntity saved = bookingRepository.save(booking);
        return mapToResponse(saved);
    }

    public List<BookingResponse> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> getBookingsByProfessional(Long professionalId) {
        return bookingRepository.findByProfessionalId(professionalId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse updateStatus(Long id, String status) {
        BookingEntity booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);
        return mapToResponse(bookingRepository.save(booking));
    }

    private BookingResponse mapToResponse(BookingEntity booking) {
        return new BookingResponse(
        	    booking.getId(),
        	    booking.getService() != null ? booking.getService().getId() : null,
        	    booking.getProfessional() != null ? booking.getProfessional().getId() : null,
        	    booking.getService() != null ? booking.getService().getName() : "-",
        	    booking.getProfessional() != null ? booking.getProfessional().getName() : "-",
        	    booking.getUser() != null ? booking.getUser().getName() : "-",
        	    booking.getBookingDate(),
        	    booking.getBookingTime(),
        	    booking.getPaymentMethod(),
        	    booking.getAmount(),
        	    booking.getStatus(),
        	    booking.getNotes()
        	);
    }
}