package com.klu.servicehubBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.servicehubBackend.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByProfessionalId(Long professionalId);
    List<ServiceEntity> findByStatus(String status);
    long countByProfessionalId(Long professionalId);
}