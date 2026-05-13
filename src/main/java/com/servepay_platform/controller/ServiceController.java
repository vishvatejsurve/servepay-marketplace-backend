package com.servepay_platform.controller;

import com.servepay_platform.dto.serviceDTO.ServiceRequestDTO;
import com.servepay_platform.dto.serviceDTO.ServiceResponseDTO;
import com.servepay_platform.service.OfferingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Service Management APIs",
description = "APIs for addService,getMyService,getAllService")
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final OfferingService offeringService;

    @Operation(
            summary = "Create Service",
            description = "Allows service providers to create and publish a new service listing"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Service added successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid service details"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Provider not found"
            )
    })
    @PostMapping
    public ResponseEntity<String> addService(@RequestBody ServiceRequestDTO dto, Authentication authentication){
       String email = authentication.getName();
        offeringService.addService(dto,email);
        return new ResponseEntity<>("Service added Successfully",HttpStatus.CREATED);
    }
    @Operation(
            summary = "Fetch provider Service",
            description = "Retrieves all services created by the authenticated provider"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service fetched successfully"),
            @ApiResponse(responseCode = "404", description = "No service found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/my")
    public ResponseEntity<List<ServiceResponseDTO>> getMyServices(Authentication authentication){
        String email = authentication.getName();
        List<ServiceResponseDTO> myServices = offeringService.getMyServices(email);
        return ResponseEntity.ok(myServices);
    }
    @Operation(
            summary = "Fetch All Services",
            description = "Retrieves all available services with provider details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services fetched successfully"),
            @ApiResponse(responseCode = "404", description = "No services found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getAllService(){
        List<ServiceResponseDTO> allService = offeringService.getAllService();
        return ResponseEntity.ok(allService);
    }

}
