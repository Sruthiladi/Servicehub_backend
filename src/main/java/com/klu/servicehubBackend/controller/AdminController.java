package com.klu.servicehubBackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.servicehubBackend.dto.AdminDashboardResponse;
import com.klu.servicehubBackend.dto.AdminProfessionalResponse;
import com.klu.servicehubBackend.dto.AdminServiceResponse;
import com.klu.servicehubBackend.dto.AdminUserResponse;
import com.klu.servicehubBackend.entity.ServiceEntity;
import com.klu.servicehubBackend.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardResponse> getDashboard() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserResponse>> getUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok("User removed successfully");
    }

    @GetMapping("/professionals")
    public ResponseEntity<List<AdminProfessionalResponse>> getProfessionals() {
        return ResponseEntity.ok(adminService.getAllProfessionals());
    }

    @GetMapping("/services")
    public ResponseEntity<List<AdminServiceResponse>> getServices() {
        return ResponseEntity.ok(adminService.getAllServices());
    }

    @PutMapping("/services/{id}/toggle-status")
    public ResponseEntity<ServiceEntity> toggleServiceStatus(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.toggleServiceStatus(id));
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        adminService.deleteService(id);
        return ResponseEntity.ok("Service removed successfully");
    }
}