package com.servepay_platform.entity;

import com.servepay_platform.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_request")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User provider;

    @ManyToOne
    private ServiceOffering service;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String address;

    private Double latitude;
    private Double longitude;

    private String problemDescription;
}
