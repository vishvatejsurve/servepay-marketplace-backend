package com.servepay_platform.service.impl;

import com.servepay_platform.dto.serviceDTO.ServiceRequestDTO;
import com.servepay_platform.dto.serviceDTO.ServiceResponseDTO;
import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.User;
import com.servepay_platform.mapper.ServiceMapper;
import com.servepay_platform.repository.ServiceRepository;
import com.servepay_platform.repository.UserRepository;
import com.servepay_platform.service.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements OfferingService {

    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    @Override
    public void addService(ServiceRequestDTO dto, String providerEmail) {
           User provider = userRepository.findByEmail(providerEmail).orElseThrow(()->new UsernameNotFoundException("Provider not found"));

          ServiceOffering service = ServiceMapper.toEntity(dto,provider);
          serviceRepository.save(service);
    }

    @Override
    public List<ServiceResponseDTO> getMyServices(String providerEmail) {
       User provider = userRepository.findByEmail(providerEmail).orElseThrow(()->new UsernameNotFoundException("Provider not found"));

       return serviceRepository.findByProvider(provider)
               .stream()
               .map(ServiceMapper::toDTO)
               .toList();
    }

    @Override
    public List<ServiceResponseDTO> getAllService() {
        return serviceRepository.findAll()
                .stream()
                .map(ServiceMapper::toDTO)
                .toList();
    }
}
