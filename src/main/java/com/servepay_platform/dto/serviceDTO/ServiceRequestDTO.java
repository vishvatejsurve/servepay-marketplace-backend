package com.servepay_platform.dto.serviceDTO;


import lombok.Data;

@Data
public class ServiceRequestDTO {
    private String title;
    private String description;
    private Double price;
}
