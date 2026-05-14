package com.servepay_platform.dto.payementDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentOrderResponseDTO {
    private String orderId;
    private Integer amount;
    private String currency;
    private String key;
}
