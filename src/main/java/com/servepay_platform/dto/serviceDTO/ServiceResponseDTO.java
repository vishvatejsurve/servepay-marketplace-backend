package com.servepay_platform.dto.serviceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String providerEmail;
}
