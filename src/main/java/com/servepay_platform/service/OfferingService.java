package com.servepay_platform.service;

import com.servepay_platform.dto.serviceDTO.ServiceRequestDTO;
import com.servepay_platform.dto.serviceDTO.ServiceResponseDTO;

import java.util.List;

public interface OfferingService {
    void addService(ServiceRequestDTO dto,String providerEmail);
    List<ServiceResponseDTO> getMyServices(String providerEmail);
    List<ServiceResponseDTO> getAllService();
}
