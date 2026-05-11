package com.servepay_platform.service;

import com.servepay_platform.dto.securityDTO.AuthResponseDTO;
import com.servepay_platform.dto.securityDTO.LoginRequestDTO;
import com.servepay_platform.dto.securityDTO.RegisterRequestDTO;

public interface AuthService {
    void registerUser(RegisterRequestDTO dto);
    AuthResponseDTO login(LoginRequestDTO request);
}
