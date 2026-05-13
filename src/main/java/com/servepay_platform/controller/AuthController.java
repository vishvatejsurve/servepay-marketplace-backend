package com.servepay_platform.controller;

import com.servepay_platform.dto.securityDTO.AuthResponseDTO;
import com.servepay_platform.dto.securityDTO.LoginRequestDTO;
import com.servepay_platform.dto.securityDTO.RegisterRequestDTO;
import com.servepay_platform.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentication APIs",
    description = "APIs for user login and registration")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register new user",
            description = "Registers customer or provider account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
            description = "User registered successfully"),
            @ApiResponse(responseCode = "400",
            description = "Invalid request"),
            @ApiResponse(
                    responseCode = "409",
                    description = "User already exists"
            )
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request){
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(
            summary = "Login user",
            description = "Authenticate user and generate JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid email or password"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request"
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }

}
