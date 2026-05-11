package com.servepay_platform.dto.securityDTO;

import com.servepay_platform.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequestDTO {

    @NotBlank(message = "Name required")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password required")
    private String password;

    private Role role;
}
