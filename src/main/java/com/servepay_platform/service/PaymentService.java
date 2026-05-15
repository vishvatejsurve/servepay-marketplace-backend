package com.servepay_platform.service;

import com.servepay_platform.dto.payementDTO.PaymentOrderResponseDTO;

public interface PaymentService {
    PaymentOrderResponseDTO createOrder(Long requestId);

    void verifyPayment(String razorpayOrderId,
                       String razorpayPaymentId,
                       String razorpaySignature,
                       Long requestId);
}
