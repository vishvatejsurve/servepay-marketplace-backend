package com.servepay_platform.dto.reviewDTO;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    private Integer rating;

    private String comment;

    private Long serviceId;
}
