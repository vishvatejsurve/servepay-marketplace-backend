package com.servepay_platform.service;

import com.servepay_platform.dto.requestDTO.RequestCreateDTO;
import com.servepay_platform.dto.requestDTO.RequestResponseDTO;

import java.util.List;

public interface RequestService {
    void createRequest(RequestCreateDTO dto,String customerEmail);
    List<RequestResponseDTO> getAvailableRequests();
    void acceptRequest(Long requestId,String providerEmail);
    void startWork(Long requestId, String providerEmail);
    void completeWork(Long requestId, String providerEmail);
}
