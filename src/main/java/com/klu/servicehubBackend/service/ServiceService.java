package com.klu.servicehubBackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.servicehubBackend.dto.ServiceRequest;
import com.klu.servicehubBackend.dto.ServiceResponse;
import com.klu.servicehubBackend.entity.ServiceEntity;
import com.klu.servicehubBackend.entity.User;
import com.klu.servicehubBackend.repository.ServiceRepository;
import com.klu.servicehubBackend.repository.UserRepository;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    public ServiceResponse addService(ServiceRequest request) {
        User professional = userRepository.findById(request.getProfessionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        ServiceEntity service = new ServiceEntity(
                request.getName(),
                request.getCategory(),
                request.getPrice(),
                request.getDuration(),
                "Active",
                professional
        );

        ServiceEntity saved = serviceRepository.save(service);
        return mapToResponse(saved);
    }

    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ServiceResponse> getServicesByProfessional(Long professionalId) {
        return serviceRepository.findByProfessionalId(professionalId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ServiceResponse toggleStatus(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setStatus(service.getStatus().equals("Active") ? "Inactive" : "Active");
        return mapToResponse(serviceRepository.save(service));
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public ServiceResponse getServiceById(Long id) {
        ServiceEntity service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return mapToResponse(service);
    }

    private ServiceResponse mapToResponse(ServiceEntity service) {
        return new ServiceResponse(
                service.getId(),
                service.getName(),
                service.getCategory(),
                service.getPrice(),
                service.getDuration(),
                service.getStatus(),
                service.getProfessional().getId(),
                service.getProfessional().getName()
        );
    }
}