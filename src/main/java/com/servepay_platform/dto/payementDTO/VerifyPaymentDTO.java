package com.servepay_platform.dto.payementDTO;

import lombok.Data;

@Data
public class VerifyPaymentDTO {
    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;
}
