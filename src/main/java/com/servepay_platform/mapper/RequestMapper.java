package com.servepay_platform.mapper;

import com.servepay_platform.dto.requestDTO.RequestCreateDTO;
import com.servepay_platform.dto.requestDTO.RequestResponseDTO;
import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.entity.ServiceRequest;
import com.servepay_platform.entity.User;
import com.servepay_platform.enums.RequestStatus;

public class RequestMapper {

    public static ServiceRequest toEntity(RequestCreateDTO dto,
                                          User customer,
                                          ServiceOffering service) {

        return ServiceRequest.builder()
                .customer(customer)
                .service(service)
                .status(RequestStatus.REQUESTED)
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .problemDescription(dto.getProblemDescription())
                .build();
    }

    public static RequestResponseDTO toDTO(ServiceRequest request) {

        return new RequestResponseDTO(
                request.getId(),
                request.getService().getTitle(),
                request.getCustomer().getEmail(),
                request.getProvider() != null ? request.getProvider().getEmail() : null,
                request.getStatus(),
                request.getAddress(),
                request.getProblemDescription()
        );
    }
}
