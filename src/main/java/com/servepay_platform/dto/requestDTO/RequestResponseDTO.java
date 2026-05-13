package com.servepay_platform.dto.requestDTO;

import com.servepay_platform.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestResponseDTO {

    private Long id;
    private String serviceTitle;
    private String customerEmail;
    private String providerEmail;
    private RequestStatus status;
    private String address;

    private String problemDescription;
}
