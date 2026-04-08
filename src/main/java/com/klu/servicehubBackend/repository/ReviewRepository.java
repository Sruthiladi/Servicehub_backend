package com.klu.servicehubBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.servicehubBackend.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByServiceId(Long serviceId);
    List<ReviewEntity> findByProfessionalId(Long professionalId);
    List<ReviewEntity> findByUserId(Long userId);
    Optional<ReviewEntity> findByBookingId(Long bookingId);
}