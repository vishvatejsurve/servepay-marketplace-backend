package com.servepay_platform.service.impl;

import com.servepay_platform.dto.requestDTO.RequestCreateDTO;
import com.servepay_platform.dto.requestDTO.RequestResponseDTO;
import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.ServiceRequest;
import com.servepay_platform.entity.User;
import com.servepay_platform.enums.RequestStatus;
import com.servepay_platform.mapper.RequestMapper;
import com.servepay_platform.repository.RequestRepository;
import com.servepay_platform.repository.ServiceRepository;
import com.servepay_platform.repository.UserRepository;
import com.servepay_platform.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public void createRequest(RequestCreateDTO dto, String customerEmail) {
      User customer = userRepository.findByEmail(customerEmail).orElseThrow(()->new RuntimeException("Customer not found"));

      ServiceOffering service= serviceRepository.findById(dto.getServiceId()).orElseThrow(()->new RuntimeException("Service not found"));

      ServiceRequest request = RequestMapper.toEntity(dto, customer, service);

      requestRepository.save(request);
    }

    @Override
    public List<RequestResponseDTO> getAvailableRequests() {
        return requestRepository.findByStatus(RequestStatus.REQUESTED)
                .stream()
                .map(RequestMapper::toDTO)
                .toList();
    }

    @Override
    public void acceptRequest(Long requestId, String providerEmail) {
        ServiceRequest request = requestRepository.findById(requestId).orElseThrow(()->new RuntimeException("Request not found"));

        User provider = userRepository.findByEmail(providerEmail)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        request.setProvider(provider);
        request.setStatus(RequestStatus.ACCEPTED);

        requestRepository.save(request);
    }

    @Override
    public void startWork(Long requestId, String providerEmail) {

        ServiceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getProvider() == null ||
                !request.getProvider().getEmail().equals(providerEmail)) {

            throw new RuntimeException("You are not assigned to this request");
        }


        if (request.getStatus() != RequestStatus.ACCEPTED) {
            throw new RuntimeException("Work can start only after ACCEPTED");
        }

        request.setStatus(RequestStatus.IN_PROGRESS);

        requestRepository.save(request);
    }

    @Override
    public void completeWork(Long requestId, String providerEmail) {
        ServiceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // check provider
        if (request.getProvider() == null ||
                !request.getProvider().getEmail().equals(providerEmail)) {

            throw new RuntimeException("You are not assigned to this request");
        }

        // status validation
        if (request.getStatus() != RequestStatus.IN_PROGRESS) {
            throw new RuntimeException("Work must be IN_PROGRESS first");
        }

        request.setStatus(RequestStatus.COMPLETED);

        requestRepository.save(request);
    }
}
