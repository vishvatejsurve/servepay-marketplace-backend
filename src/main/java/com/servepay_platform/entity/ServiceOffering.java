package com.servepay_platform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_offering")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Double price;
    private Double averageRating;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

}
