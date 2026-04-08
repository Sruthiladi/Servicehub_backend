package com.klu.servicehubBackend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.servicehubBackend.dto.ReviewRequest;
import com.klu.servicehubBackend.dto.ReviewResponse;
import com.klu.servicehubBackend.entity.BookingEntity;
import com.klu.servicehubBackend.entity.ReviewEntity;
import com.klu.servicehubBackend.entity.ServiceEntity;
import com.klu.servicehubBackend.entity.User;
import com.klu.servicehubBackend.repository.BookingRepository;
import com.klu.servicehubBackend.repository.ReviewRepository;
import com.klu.servicehubBackend.repository.ServiceRepository;
import com.klu.servicehubBackend.repository.UserRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         BookingRepository bookingRepository,
                         ServiceRepository serviceRepository,
                         UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public ReviewResponse createReview(ReviewRequest request) {
        BookingEntity booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!"Completed".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("You can only review completed bookings");
        }

        if (reviewRepository.findByBookingId(request.getBookingId()).isPresent()) {
            throw new RuntimeException("Review already submitted for this booking");
        }

        ServiceEntity service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User professional = userRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        ReviewEntity review = new ReviewEntity(
                request.getRating(),
                request.getComment(),
                LocalDate.now().toString(),
                booking,
                service,
                user,
                professional
        );

        ReviewEntity saved = reviewRepository.save(review);

        return new ReviewResponse(
                saved.getId(),
                saved.getBooking().getId(),
                saved.getService().getId(),
                saved.getProfessional().getId(),
                saved.getUser().getName(),
                saved.getRating(),
                saved.getComment(),
                saved.getCreatedAt()
        );
    }

    public List<ReviewResponse> getReviewsByService(Long serviceId) {
        List<ReviewEntity> reviews = reviewRepository.findByServiceId(serviceId);
        return mapReviews(reviews);
    }

    public List<ReviewResponse> getReviewsByProfessional(Long professionalId) {
        List<ReviewEntity> reviews = reviewRepository.findByProfessionalId(professionalId);
        return mapReviews(reviews);
    }

    public List<ReviewResponse> getReviewsByUser(Long userId) {
        List<ReviewEntity> reviews = reviewRepository.findByUserId(userId);
        return mapReviews(reviews);
    }

    public ReviewResponse getReviewByBooking(Long bookingId) {
        ReviewEntity review = reviewRepository.findByBookingId(bookingId).orElse(null);

        if (review == null) return null;

        return new ReviewResponse(
                review.getId(),
                review.getBooking().getId(),
                review.getService().getId(),
                review.getProfessional().getId(),
                review.getUser().getName(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }

    private List<ReviewResponse> mapReviews(List<ReviewEntity> reviews) {
        List<ReviewResponse> response = new ArrayList<>();

        for (ReviewEntity review : reviews) {
            response.add(new ReviewResponse(
                    review.getId(),
                    review.getBooking().getId(),
                    review.getService().getId(),
                    review.getProfessional().getId(),
                    review.getUser().getName(),
                    review.getRating(),
                    review.getComment(),
                    review.getCreatedAt()
            ));
        }

        return response;
    }
}