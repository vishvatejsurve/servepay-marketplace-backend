package com.servepay_platform.service.impl;

import com.servepay_platform.dto.securityDTO.AuthResponseDTO;
import com.servepay_platform.dto.securityDTO.LoginRequestDTO;
import com.servepay_platform.dto.securityDTO.RegisterRequestDTO;
import com.servepay_platform.entity.User;
import com.servepay_platform.mapper.AuthMapper;
import com.servepay_platform.repository.UserRepository;
import com.servepay_platform.service.AuthService;
import com.servepay_platform.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager manager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void registerUser(RegisterRequestDTO dto) {
        User user= User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
       User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));


       manager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
       );

       String token= jwtUtil.generateToken(user.getEmail());
        return AuthMapper.toAuthResponse(token,user);
    }
}
