package com.servepay_platform.dto.requestDTO;

import lombok.Data;

@Data
public class RequestCreateDTO {

    private Long serviceId;
    private String address;
    private Double latitude;
    private Double longitude;

    private String problemDescription;
}
