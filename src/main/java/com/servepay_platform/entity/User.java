package com.servepay_platform.entity;


import com.servepay_platform.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Double latitude;
    private Double longitude;
}
