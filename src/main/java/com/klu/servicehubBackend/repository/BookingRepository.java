package com.klu.servicehubBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.servicehubBackend.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByUserId(Long userId);
    List<BookingEntity> findByProfessionalId(Long professionalId);
}