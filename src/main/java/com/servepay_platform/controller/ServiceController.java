package com.servepay_platform.controller;

import com.servepay_platform.dto.serviceDTO.ServiceRequestDTO;
import com.servepay_platform.dto.serviceDTO.ServiceResponseDTO;
import com.servepay_platform.entity.ServiceOffering;
import com.servepay_platform.service.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final OfferingService offeringService;

    @PostMapping
    public ResponseEntity<String> addService(@RequestBody ServiceRequestDTO dto, Authentication authentication){
       String email = authentication.getName();
        offeringService.addService(dto,email);
        return new ResponseEntity<>("Service added Successfully",HttpStatus.CREATED);
    }
    @GetMapping("/my")
    public ResponseEntity<List<ServiceResponseDTO>> getMyServices(Authentication authentication){
        String email = authentication.getName();
        List<ServiceResponseDTO> myServices = offeringService.getMyServices(email);
        return ResponseEntity.ok(myServices);
    }
    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getAllService(){
        List<ServiceResponseDTO> allService = offeringService.getAllService();
        return ResponseEntity.ok(allService);
    }

}
