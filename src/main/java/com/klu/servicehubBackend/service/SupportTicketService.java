package com.klu.servicehubBackend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.servicehubBackend.dto.SupportTicketRequest;
import com.klu.servicehubBackend.dto.SupportTicketResponse;
import com.klu.servicehubBackend.entity.SupportTicketEntity;
import com.klu.servicehubBackend.entity.User;
import com.klu.servicehubBackend.repository.SupportTicketRepository;
import com.klu.servicehubBackend.repository.UserRepository;

@Service
public class SupportTicketService {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Autowired
    private UserRepository userRepository;

    public SupportTicketResponse createTicket(SupportTicketRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SupportTicketEntity ticket = new SupportTicketEntity(
                request.getSubject(),
                request.getCategory(),
                request.getMessage(),
                request.getPriority(),
                "Open",
                LocalDate.now().toString(),
                user
        );

        SupportTicketEntity saved = supportTicketRepository.save(ticket);
        return mapToResponse(saved);
    }

    public List<SupportTicketResponse> getAllTickets() {
        return supportTicketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<SupportTicketResponse> getOpenAndInProgressTickets() {
        return supportTicketRepository.findAll()
                .stream()
                .filter(ticket -> !ticket.getStatus().equals("Resolved"))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<SupportTicketResponse> getResolvedTickets() {
        return supportTicketRepository.findByStatus("Resolved")
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<SupportTicketResponse> getTicketsByUser(Long userId) {
        return supportTicketRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SupportTicketResponse updateStatus(Long id, String status, String resolvedBy) {
        SupportTicketEntity ticket = supportTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(status);

        if (status.equals("Resolved")) {
            ticket.setResolvedAt(LocalDate.now().toString());
            ticket.setResolvedBy(resolvedBy != null ? resolvedBy : "Support Team");
        }

        SupportTicketEntity updated = supportTicketRepository.save(ticket);
        return mapToResponse(updated);
    }

    private SupportTicketResponse mapToResponse(SupportTicketEntity ticket) {
        return new SupportTicketResponse(
                ticket.getId(),
                ticket.getUser().getName(),
                ticket.getSubject(),
                ticket.getCategory(),
                ticket.getMessage(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreatedAt(),
                ticket.getResolvedAt(),
                ticket.getResolvedBy()
        );
    }
}