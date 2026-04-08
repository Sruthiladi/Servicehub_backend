package com.klu.servicehubBackend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.servicehubBackend.dto.SupportTicketRequest;
import com.klu.servicehubBackend.dto.SupportTicketResponse;
import com.klu.servicehubBackend.service.SupportTicketService;

@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "http://localhost:5173")
public class SupportTicketController {

    @Autowired
    private SupportTicketService supportTicketService;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody SupportTicketRequest request) {
        try {
            return ResponseEntity.ok(supportTicketService.createTicket(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<SupportTicketResponse>> getAllTickets() {
        return ResponseEntity.ok(supportTicketService.getAllTickets());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SupportTicketResponse>> getActiveTickets() {
        return ResponseEntity.ok(supportTicketService.getOpenAndInProgressTickets());
    }

    @GetMapping("/resolved")
    public ResponseEntity<List<SupportTicketResponse>> getResolvedTickets() {
        return ResponseEntity.ok(supportTicketService.getResolvedTickets());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SupportTicketResponse>> getTicketsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(supportTicketService.getTicketsByUser(userId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(
                    supportTicketService.updateStatus(id, body.get("status"), body.get("resolvedBy"))
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}