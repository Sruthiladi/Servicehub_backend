package com.klu.servicehubBackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klu.servicehubBackend.dto.AdminDashboardResponse;
import com.klu.servicehubBackend.dto.AdminProfessionalResponse;
import com.klu.servicehubBackend.dto.AdminServiceResponse;
import com.klu.servicehubBackend.dto.AdminUserResponse;
import com.klu.servicehubBackend.entity.ServiceEntity;
import com.klu.servicehubBackend.entity.User;
import com.klu.servicehubBackend.repository.BookingRepository;
import com.klu.servicehubBackend.repository.ServiceRepository;
import com.klu.servicehubBackend.repository.SupportTicketRepository;
import com.klu.servicehubBackend.repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final SupportTicketRepository supportTicketRepository;
    private final ServiceRepository serviceRepository;

    public AdminService(UserRepository userRepository,
                        BookingRepository bookingRepository,
                        SupportTicketRepository supportTicketRepository,
                        ServiceRepository serviceRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.supportTicketRepository = supportTicketRepository;
        this.serviceRepository = serviceRepository;
    }

    public AdminDashboardResponse getDashboardStats() {
        long totalUsers = userRepository.countByRole("user");
        long totalProfessionals = userRepository.countByRole("professional");
        long totalBookings = bookingRepository.count();
        long openTickets = supportTicketRepository.countByStatusNot("Resolved");

        return new AdminDashboardResponse(totalUsers, totalProfessionals, totalBookings, openTickets);
    }

    public List<AdminUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<AdminUserResponse> response = new ArrayList<>();

        for (User user : users) {
            response.add(new AdminUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole(),
                    "Active",
                    "-"
            ));
        }

        return response;
    }

    public List<AdminProfessionalResponse> getAllProfessionals() {
        List<User> professionals = userRepository.findByRole("professional");
        List<AdminProfessionalResponse> response = new ArrayList<>();

        for (User professional : professionals) {
            List<ServiceEntity> services = serviceRepository.findByProfessionalId(professional.getId());

            String category = services.isEmpty() ? "-" : services.get(0).getCategory();

            response.add(new AdminProfessionalResponse(
                    professional.getId(),
                    professional.getName(),
                    professional.getEmail(),
                    category,
                    "Active"
            ));
        }

        return response;
    }

    public List<AdminServiceResponse> getAllServices() {
        List<ServiceEntity> services = serviceRepository.findAll();
        List<AdminServiceResponse> response = new ArrayList<>();

        for (ServiceEntity service : services) {
            response.add(new AdminServiceResponse(
                    service.getId(),
                    service.getName(),
                    service.getCategory(),
                    service.getPrice(),
                    service.getDuration(),
                    service.getStatus(),
                    service.getProfessional() != null ? service.getProfessional().getName() : "-"
            ));
        }

        return response;
    }

    public ServiceEntity toggleServiceStatus(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        if ("Active".equalsIgnoreCase(service.getStatus())) {
            service.setStatus("Inactive");
        } else {
            service.setStatus("Active");
        }

        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}