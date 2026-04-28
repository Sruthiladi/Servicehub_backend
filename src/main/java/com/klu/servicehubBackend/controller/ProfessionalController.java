package com.klu.servicehubBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.servicehubBackend.dto.ProfessionalBookingResponse;
import com.klu.servicehubBackend.dto.ProfessionalDashboardResponse;
import com.klu.servicehubBackend.dto.ProfessionalEarningsResponse;
import com.klu.servicehubBackend.service.ProfessionalService;

@RestController
@RequestMapping("/api/professional")
@CrossOrigin(origins = "*")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/{professionalId}/dashboard")
    public ResponseEntity<ProfessionalDashboardResponse> getDashboard(@PathVariable Long professionalId) {
        return ResponseEntity.ok(professionalService.getDashboard(professionalId));
    }

    @GetMapping("/{professionalId}/earnings")
    public ResponseEntity<ProfessionalEarningsResponse> getEarnings(@PathVariable Long professionalId) {
        return ResponseEntity.ok(professionalService.getEarnings(professionalId));
    }

    @GetMapping("/{professionalId}/earnings/bookings")
    public ResponseEntity<List<ProfessionalBookingResponse>> getEarningBookings(@PathVariable Long professionalId) {
        return ResponseEntity.ok(professionalService.getEarningBookings(professionalId));
    }
}