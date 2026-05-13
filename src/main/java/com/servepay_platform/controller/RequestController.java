package com.servepay_platform.controller;

import com.servepay_platform.dto.requestDTO.RequestCreateDTO;
import com.servepay_platform.dto.requestDTO.RequestResponseDTO;
import com.servepay_platform.mapper.RequestMapper;
import com.servepay_platform.service.RequestService;
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
@Tag(name = "Booking & Request APIs",
description = "APIs for booking services and managing customer requests")
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @Operation(
            summary = "Create Booking Request",
            description = "Allows users to create a service booking request"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid booking details"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access"),
            @ApiResponse(responseCode = "404", description = "Service not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<String> createRequest(@RequestBody RequestCreateDTO dto, Authentication authentication){
        String email= authentication.getName();
        requestService.createRequest(dto, email);
        return new ResponseEntity<>("Request Created Successfully",HttpStatus.CREATED);
    }
    @Operation(
            summary = "Fetch User Requests",
            description = "Retrieves all booking requests associated with authenticated user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requests fetched successfully"),
            @ApiResponse(responseCode = "404", description = "No requests found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/available")
    public ResponseEntity<List<RequestResponseDTO>> getAvailableRequest(){
        List<RequestResponseDTO> availableRequests = requestService.getAvailableRequests();
        return ResponseEntity.ok(availableRequests);
    }

    @Operation(
            summary = "Accept Service Request",
            description = "Allows a service provider to accept a customer request"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Request accepted successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Request not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Request already accepted"
            )
    })
    @PutMapping("/{id}/accept")
    public String acceptRequest(@PathVariable Long id,
                                Authentication authentication) {

        String email = authentication.getName();
        requestService.acceptRequest(id, email);

        return "Request accepted";
    }

    @Operation(
            summary = "Start Service Work",
            description = "Marks the accepted request as work in progress"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Work started successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Request not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request state"
            )
    })
    @PutMapping("/{id}/start")
    public String startWork(@PathVariable Long id,
                            Authentication authentication) {

        String email = authentication.getName();

        requestService.startWork(id, email);

        return "Work started";
    }
    @Operation(
            summary = "Complete Service Work",
            description = "Marks the service request as completed"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Work completed successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized access"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Request not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request state"
            )
    })
    @PutMapping("/{id}/complete")
    public String completeWork(@PathVariable Long id,
                               Authentication authentication) {

        String email = authentication.getName();

        requestService.completeWork(id, email);

        return "Work completed";
    }
}
