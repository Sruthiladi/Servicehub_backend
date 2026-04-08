package com.klu.servicehubBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.servicehubBackend.entity.SupportTicketEntity;

public interface SupportTicketRepository extends JpaRepository<SupportTicketEntity, Long> {
    List<SupportTicketEntity> findByStatus(String status);
    List<SupportTicketEntity> findByUserId(Long userId);
    long countByStatusNot(String status);
}