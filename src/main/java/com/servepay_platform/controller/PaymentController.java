package com.servepay_platform.controller;

import com.servepay_platform.dto.payementDTO.PaymentOrderResponseDTO;
import com.servepay_platform.dto.payementDTO.VerifyPaymentDTO;
import com.servepay_platform.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(
        name = "Payment APIs",
        description = "APIs for creating and verifying Razorpay payments"
)
@RequestMapping("/payments")
@RequiredArgsConstructor

public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            summary = "Create Payment Order",
            description = "Creates a Razorpay order for a specific service request"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Payment order created successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Request not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            )
    })
    @PostMapping("/create-order/{requestId}")
    public PaymentOrderResponseDTO createOrder(@PathVariable Long requestId) {

        return paymentService.createOrder(requestId);
    }

    @Operation(
            summary = "Verify Payment",
            description = "Verifies Razorpay payment signature and marks payment as successful"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Payment verified successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid payment signature"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Request not found"
            )
    })
    @PostMapping("/verify/{requestId}")
    public String verifyPayment(@PathVariable Long requestId,
                                @RequestBody VerifyPaymentDTO dto) {

        paymentService.verifyPayment(
                dto.getRazorpayOrderId(),
                dto.getRazorpayPaymentId(),
                dto.getRazorpaySignature(),
                requestId
        );

        return "Payment successful";
    }
}
