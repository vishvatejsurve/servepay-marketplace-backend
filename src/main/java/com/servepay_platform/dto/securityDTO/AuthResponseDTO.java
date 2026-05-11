package com.servepay_platform.dto.securityDTO;

import com.servepay_platform.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class AuthResponseDTO {
    private String token;
    private String email;
    private Role role;
}
